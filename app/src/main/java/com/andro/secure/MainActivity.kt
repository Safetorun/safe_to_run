package com.andro.secure

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.andro.secure.databinding.ActivityMainBinding
import io.github.dllewellyn.safetorun.SafeToRun
import io.github.dllewellyn.safetorun.conditional.conditionalBuilder
import io.github.dllewellyn.safetorun.configure
import io.github.dllewellyn.safetorun.features.blacklistedapps.blacklistConfiguration
import io.github.dllewellyn.safetorun.features.debug.debugCheck
import io.github.dllewellyn.safetorun.features.oscheck.OSConfiguration.minOsVersion
import io.github.dllewellyn.safetorun.features.oscheck.OSConfiguration.notManufacturer
import io.github.dllewellyn.safetorun.features.oscheck.osDetectionCheck
import io.github.dllewellyn.safetorun.features.rootdetection.rootDetection
import io.github.dllewellyn.safetorun.features.signatureverify.verifySignatureConfig
import io.github.dllewellyn.safetorun.reporting.toGrouped

class MainActivity : AppCompatActivity() {

    private val reportsController = ResultEpoxyController()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        SafeToRun.init(
            configure {

                // Root beer (detect root)
                rootDetection {
                    tolerateRoot = false
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
                    }
                ).warn()

                debugCheck().warn()
            }
        )
        binding.reportList.setController(reportsController)
        reportsController.setData(SafeToRun.isSafeToRun().toGrouped())
    }

    companion object {
        const val MIN_OS_VERSION = 41
    }
}
