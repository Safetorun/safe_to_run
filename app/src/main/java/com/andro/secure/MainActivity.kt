package com.andro.secure

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
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
import com.andro.secure.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        SafeToRun.init(
            configure {

                // Root beer (detect root)
                this errorIf rootDetection {
                    tolerateRoot = false
                    tolerateBusyBox = true
                }


                // Black list certain apps
                this errorIf blacklistConfiguration {
                    +"com.abc.def"
                    +"com.google.earth"
                }

                this errorIf verifySignatureConfig("cSP1O3JN/8+Ag14WAOeOEnwAnpY=")

                // OS Blacklist version
                this warnIf osDetectionCheck(
                    conditionalBuilder {
                        with(minOsVersion(22))
                        and(notManufacturer("Abc"))
                    }
                )

                this warnIf debugCheck()

            }
        )

        val safeToRun = SafeToRun.isSafeToRun()
        Log.v("SafeToRun", safeToRun.toString())
    }
}

