package com.andro.secure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.andro.secure.databinding.ActivityMainBinding
import com.safetorun.intents.url.params.AllowedType
import com.safetorun.intents.url.urlVerification


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
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

    fun verify(urlToVerify: String) {

        // https://3.basecamp.com/verify?proceed_to=https://dodgywebsite.co.uk
        urlToVerify.urlVerification {
            "3.basecamp.com".allowHost()
            allowParameter {
                parameterName = "proceed_to"
                allowedType = AllowedType.Url {
                    "3.basecamp.com".allowHost()
                }
            }
        }
    }
}
