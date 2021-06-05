package com.andro.secure

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.andro.secure.databinding.ActivityMainBinding
import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import io.github.dllewellyn.safetorun.SafeToRun
import io.github.dllewellyn.safetorun.conditional.conditionalBuilder
import io.github.dllewellyn.safetorun.configure
import io.github.dllewellyn.safetorun.features.blacklistedapps.blacklistConfiguration
import io.github.dllewellyn.safetorun.features.debug.debugCheck
import io.github.dllewellyn.safetorun.features.installorigin.installOriginCheckWithDefaults
import io.github.dllewellyn.safetorun.features.oscheck.bannedModel
import io.github.dllewellyn.safetorun.features.oscheck.minOsVersion
import io.github.dllewellyn.safetorun.features.oscheck.notManufacturer
import io.github.dllewellyn.safetorun.features.oscheck.osDetectionCheck
import io.github.dllewellyn.safetorun.features.rootdetection.rootDetection
import io.github.dllewellyn.safetorun.features.signatureverify.verifySignatureConfig
import io.github.dllewellyn.safetorun.offdevice.deviceInformation
import io.github.dllewellyn.safetorun.offdevice.safeToRunOffDevice
import io.github.dllewellyn.safetorun.reporting.toGrouped
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val reportsController = ResultEpoxyController()

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

                installOriginCheckWithDefaults().warn()

                debugCheck().warn()
            }
        )
        binding.reportList.setController(reportsController)
        reportsController.setData(SafeToRun.isSafeToRun().toGrouped(), mapOf())
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
