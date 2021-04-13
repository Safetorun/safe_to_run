package com.andro.secure

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.andro.safetorun.SafeToRun
import com.andro.safetorun.configure
import com.andro.safetorun.features.rootdetection.rootDetection
import com.andro.secure.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
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
                }

                // Black list certain apps

            }
        )

        binding.safeToRunResult.text = getString(R.string.safe_to_run_result, SafeToRun.isSafeToRun(this))
    }
}