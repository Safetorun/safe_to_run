package com.andro.secure

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.andro.secure.databinding.ActivityMainBinding
import com.safetorun.features.blacklistedapps.blacklistedAppCheck
import com.safetorun.features.installorigin.installOriginCheckWithDefaultsCheck
import com.safetorun.features.oscheck.bannedBoardCheck
import com.safetorun.features.oscheck.bannedHardwareCheck
import com.safetorun.features.oscheck.emulator.banAvdEmulatorCheck
import com.safetorun.features.oscheck.emulator.banBluestacksEmulatorCheck
import com.safetorun.features.oscheck.emulator.banGenymotionEmulatorCheck
import com.safetorun.features.oscheck.safeToRunCombinedCheck
import com.safetorun.features.rootdetection.rootDetectionCheck
import com.safetorun.inline.logger
import com.safetorun.inline.safeToRunWithLogger
import com.safetorun.intents.file.verifyFile
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        canIRun("MainActivityCheck") {
            throw RuntimeException("Failed to start activity!")
        }
    }

    private inline fun Context.canIRun(checkName: String, actionOnFailure: () -> Unit) {

        if (safeToRunWithLogger(
                logger = logger("CEnQEwYzpV8O05Q5x8lGe5rijnvqcxAk4EuTD3bW", checkName),
                { banAvdEmulatorCheck() },
                { banGenymotionEmulatorCheck() },
                { banBluestacksEmulatorCheck() },
                { blacklistedAppCheck("Test app", "Test app 2") },
                { rootDetectionCheck() },
                {
                    safeToRunCombinedCheck(
                        listOf(
                            { bannedHardwareCheck("hardware") },
                            { bannedBoardCheck("board") }
                        )
                    )
                },
                {
                    safeToRunCombinedCheck(
                        listOf { installOriginCheckWithDefaultsCheck() },
                        listOf { !BuildConfig.DEBUG }
                    )
                },
            )()
        ) {
            actionOnFailure()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.webView) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_reportFragment_to_webViewSample)
        }

        return super.onOptionsItemSelected(item)
    }

    fun verify(fullFilenameToVerify: String) {

        // Full name to verify = /ok/to/downloadto/../../filename.txt - reject!
        File(fullFilenameToVerify).verifyFile(this) {
            File("/ok/to/downloadto").allowDirectory(allowSubdirectories = false)
        }


    }
}
