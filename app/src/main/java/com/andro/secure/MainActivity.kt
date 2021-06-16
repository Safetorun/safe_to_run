package com.andro.secure

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.andro.secure.databinding.ActivityMainBinding
import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import io.github.dllewellyn.safetorun.SafeToRun
import io.github.dllewellyn.safetorun.conditional.conditionalBuilder
import io.github.dllewellyn.safetorun.configure
import io.github.dllewellyn.safetorun.features.blacklistedapps.blacklistConfiguration
import io.github.dllewellyn.safetorun.features.blacklistedapps.blacklistedAppCheck
import io.github.dllewellyn.safetorun.features.blacklistedapps.rooting.blacklistRootingApps
import io.github.dllewellyn.safetorun.features.debug.debugCheck
import io.github.dllewellyn.safetorun.features.debug.isDebuggableCheck
import io.github.dllewellyn.safetorun.features.installorigin.installOriginCheckWithDefaults
import io.github.dllewellyn.safetorun.features.installorigin.installOriginCheckWithDefaultsCheck
import io.github.dllewellyn.safetorun.features.oscheck.banAvdEmulator
import io.github.dllewellyn.safetorun.features.oscheck.banBluestacksEmulator
import io.github.dllewellyn.safetorun.features.oscheck.bannedBoardCheck
import io.github.dllewellyn.safetorun.features.oscheck.bannedHardwareCheck
import io.github.dllewellyn.safetorun.features.oscheck.bannedModel
import io.github.dllewellyn.safetorun.features.oscheck.emulator.banAvdEmulatorCheck
import io.github.dllewellyn.safetorun.features.oscheck.emulator.banBluestacksEmulatorCheck
import io.github.dllewellyn.safetorun.features.oscheck.emulator.banGenymotionEmulatorCheck
import io.github.dllewellyn.safetorun.features.oscheck.minOsVersion
import io.github.dllewellyn.safetorun.features.oscheck.notManufacturer
import io.github.dllewellyn.safetorun.features.oscheck.osDetectionCheck
import io.github.dllewellyn.safetorun.features.oscheck.safeToRunCombinedCheck
import io.github.dllewellyn.safetorun.features.rootdetection.rootDetection
import io.github.dllewellyn.safetorun.features.rootdetection.rootDetectionCheck
import io.github.dllewellyn.safetorun.features.signatureverify.verifySignatureCheck
import io.github.dllewellyn.safetorun.features.signatureverify.verifySignatureConfig
import io.github.dllewellyn.safetorun.inline.buildSafeToRunCheckList
import io.github.dllewellyn.safetorun.inline.safeToRun
import io.github.dllewellyn.safetorun.offdevice.deviceInformation
import io.github.dllewellyn.safetorun.offdevice.safeToRunOffDevice
import io.github.dllewellyn.safetorun.reporting.toGrouped
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val reportsController = ResultEpoxyController()

    private inline fun canIRun(actionOnFailure: () -> Unit) {
        if (safeToRun(buildSafeToRunCheckList {
                add {
                    banAvdEmulatorCheck()
                }

                add {
                    banGenymotionEmulatorCheck()
                }

                add {
                    banBluestacksEmulatorCheck()
                }

                add {
                    blacklistedAppCheck()
                }

                add {
                    rootDetectionCheck()
                }

                add {
                    isDebuggableCheck()
                }

                add {
                    safeToRunCombinedCheck(
                        listOf(
                            { bannedHardwareCheck("hardware") },
                            { bannedBoardCheck("board") }
                        )
                    )
                }

                add {
                    safeToRunCombinedCheck(
                        listOf { installOriginCheckWithDefaultsCheck() },
                        listOf { !BuildConfig.DEBUG }
                    )
                }

                add {
                    verifySignatureCheck("Abc")
                }

            })()) {
            actionOnFailure()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        Log.v("Device information", deviceInformation().toString())

        safeToRunOffDevice(BuildConfig.SERVER_URL, BuildConfig.API_KEY).isSafeToRun {
            // Send this result to the server for verification.
            // This sample is for demonstration purposes only
            Log.v("IsSafeToRun", it.signedResult)

            // In reality, you need to use JWT.verify() with your secret
            reportsController.setData(reportsController.reports, JWT.decode(it.signedResult).verifierResult())
        }

        binding.runSensitiveAction.setOnClickListener {
            canIRun {
                throw RuntimeException("Def")
            }

            Toast.makeText(it.context, "Performed sensitive action!!", Toast.LENGTH_LONG)
                .show()

        }

        configureSafeToRunReporting()
        binding.reportList.setController(reportsController)

        canIRun { Log.v("Failure", "Failure") }

        reportsController.setData(SafeToRun.isSafeToRun().toGrouped(), mapOf())
    }

    private fun configureSafeToRunReporting() {
        SafeToRun.init(
            configure {

                // Root beer (detect root)
                rootDetection {
                    tolerateBusyBox = true
                }.error()

                // Black list certain apps
                blacklistConfiguration {
                    +"com.abc.def"
                    +packageName
                    blacklistRootingApps()
                }.error()

                verifySignatureConfig("cSP1O3JN/8+Ag14WAOeOEnwAnpY=")
                    .error()

                // OS Blacklist version
                osDetectionCheck(
                    conditionalBuilder {
                        with(minOsVersion(MIN_OS_VERSION))
                        and(notManufacturer("Abc"))
                        and(bannedModel("bannedModel"))
                    }
                ).warn()

                osDetectionCheck(
                    conditionalBuilder {
                        with(bannedModel("Pixel 4a (5G)"))
                    }
                ).warn()

                osDetectionCheck(banBluestacksEmulator()).error()

                installOriginCheckWithDefaults().warn()

                osDetectionCheck(banAvdEmulator()).error()
                debugCheck().warn()
            }
        )
    }

    private fun DecodedJWT.verifierResult(): Map<String, String> =
        mapOf(
            "correctIssuer" to (issuer == BuildConfig.API_KEY),
            "anyFailures" to ((claims[Errors]?.asInt() ?: 1) > 0),
            "expired" to expiresAt.after(Date()),
            "anyWarnings" to ((claims[Warnings]?.asInt() ?: 1) > 0),
            "anyPasses" to ((claims[Passes]?.asInt() ?: 1) > 0)
        ).mapValues { it.value.toString() }

    companion object {
        const val MIN_OS_VERSION = 41
        const val Warnings = "warnings"
        const val Errors = "errors"
        const val Passes = "passes"
    }
}
