package com.andro.secure

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
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
import com.andro.secure.databinding.ActivityMainBinding
import java.security.MessageDigest


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val sigs = packageManager
            .getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES).signingInfo

        sigs.apkContentsSigners.forEach {
            Log.v("Signature", it.toCharsString())
        }

        checkAppSignature(this)

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
    }

    companion object {
        const val SIGNATURE: String = "abc"
    }

    fun checkAppSignature(context: Context): Int {
        try {
            val packageInfo: PackageInfo =
                context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_SIGNATURES)
            for (signature in packageInfo.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val currentSignature: String = Base64.encodeToString(md.digest(), Base64.DEFAULT)
                Log.d("REMOVE_ME", "Include this string as a value for SIGNATURE:$currentSignature")

                //compare signatures
                if (SIGNATURE == currentSignature) {
                    return 1
                }
            }
        } catch (e: Exception) {
            //assumes an issue in checking signature., but we let the caller decide on what to do.
        }
        return -1
    }
}

