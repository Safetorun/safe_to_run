package com.andro.secure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.andro.secure.databinding.ActivityMainBinding
import com.safetorun.intents.file.verifyFile
import com.safetorun.logger.getLogs
import com.safetorun.logger.logCheckSuccess
import com.safetorun.offdevice.safeToRunLogger
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        logCheckSuccess("MainActivity")

        val logger = safeToRunLogger(
            "https://api.safetorun.com",
            "xTzr6DFulc7PuEfQyGxzr3qn9kbzvCBv12clOyAr"
        )

        getLogs {
            logger.invoke(it)
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
