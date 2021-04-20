package com.andro.secure

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.andro.safetorun.SafeToRun
import com.andro.safetorun.conditional.conditionalBuilder
import com.andro.safetorun.configure
import com.andro.safetorun.features.blacklistedapps.blacklistConfiguration
import com.andro.safetorun.features.oscheck.OSConfiguration.minOsVersion
import com.andro.safetorun.features.oscheck.OSConfiguration.notManufacturer
import com.andro.safetorun.features.oscheck.osDetectionCheck
import com.andro.safetorun.features.rootdetection.rootDetection
import com.andro.safetorun.features.signatureverify.verifySignatureConfig
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
                plus {
                    rootDetection {
                        tolerateRoot = false
                        tolerateBusyBox = true
                    }
                }


                // Black list certain apps
                plus {
                    blacklistConfiguration {
                        +"com.abc.def"
                        +"com.google.earth"
                    }
                }
                plus(
                    verifySignatureConfig("cSP1O3JN/8+Ag14WAOeOEnwAnpY=")
                )

                // OS Blacklist version
                plus {
                    osDetectionCheck(
                        conditionalBuilder {
                            with(minOsVersion(22))
                            and(notManufacturer("Abc"))
                        }
                    )
                }
            }
        )

        val safeToRun = SafeToRun.isSafeToRun()
        Log.v("SafeToRun", safeToRun.toString())
    }
}

