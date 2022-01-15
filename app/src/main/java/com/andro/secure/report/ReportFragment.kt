package com.andro.secure.report

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.andro.secure.BuildConfig
import com.andro.secure.ProtectedActivity
import com.andro.secure.ResultEpoxyController
import com.andro.secure.databinding.ReportFragmentBinding
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
import io.github.dllewellyn.safetorun.features.oscheck.*
import io.github.dllewellyn.safetorun.features.oscheck.emulator.banAvdEmulatorCheck
import io.github.dllewellyn.safetorun.features.oscheck.emulator.banBluestacksEmulatorCheck
import io.github.dllewellyn.safetorun.features.oscheck.emulator.banGenymotionEmulatorCheck
import io.github.dllewellyn.safetorun.features.rootdetection.rootDetection
import io.github.dllewellyn.safetorun.features.rootdetection.rootDetectionCheck
import io.github.dllewellyn.safetorun.features.signatureverify.verifySignatureCheck
import io.github.dllewellyn.safetorun.features.signatureverify.verifySignatureConfig
import io.github.dllewellyn.safetorun.inline.buildSafeToRunCheckList
import io.github.dllewellyn.safetorun.inline.safeToRun
import io.github.dllewellyn.safetorun.reporting.toGrouped
import java.util.*

class ReportFragment : Fragment() {

    private val reportsController = ResultEpoxyController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        ReportFragmentBinding.inflate(inflater, container, false)
            .apply { setup(this) }
            .root

    private fun setup(binding: ReportFragmentBinding) {
        binding.runSensitiveAction.setOnClickListener {
            requireContext().canIRun {
                throw RuntimeException("Def")
            }

            Toast.makeText(it.context, "Performed sensitive action!!", Toast.LENGTH_LONG)
                .show()
        }

        binding.startProtectedActivity.setOnClickListener {
            startActivity(Intent(requireContext(), ProtectedActivity::class.java))
        }

        configureSafeToRunReporting()
        binding.reportList.setController(reportsController)

        requireContext().canIRun { Log.v("Failure", "Failure") }

        reportsController.setData(SafeToRun.isSafeToRun().toGrouped(), mapOf())
    }

    private fun configureSafeToRunReporting() {
        requireContext().apply {
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
    }

    private fun DecodedJWT.verifierResult(): Map<String, String> =
        mapOf(
            "correctIssuer" to (issuer == BuildConfig.API_KEY),
            "anyFailures" to ((claims[Errors]?.asInt() ?: 1) > 0),
            "expired" to expiresAt.after(Date()),
            "anyWarnings" to ((claims[Warnings]?.asInt() ?: 1) > 0),
            "anyPasses" to ((claims[Passes]?.asInt() ?: 1) > 0)
        ).mapValues { it.value.toString() }


    private inline fun Context.canIRun(actionOnFailure: () -> Unit) {
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

    companion object {
        const val MIN_OS_VERSION = 41
        const val Warnings = "warnings"
        const val Errors = "errors"
        const val Passes = "passes"
    }
}
