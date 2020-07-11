package com.andro.secure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andro.safetorun.SafeToRun
import com.andro.safetorun.SafeToRunConfiguration
import com.andro.safetorun.configure
import com.andro.safetorun.features.blacklistedapps.BlacklistedAppCheck
import com.andro.safetorun.features.rootdetection.rootDetection
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SafeToRun.init(
            configure(this) {

                // Root beer (detect root)
                rootDetection {
                    tolerateRoot = false
                    tolerateBusyBox = true
                }

                // Black list certain apps

            }
        )

        safeToRunResult.text = getString(R.string.safe_to_run_result, SafeToRun.isSafeToRun())
    }
}