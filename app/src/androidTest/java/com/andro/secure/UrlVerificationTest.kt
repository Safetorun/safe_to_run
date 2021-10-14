package com.andro.secure

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.andro.secure.intents.WebViewActivity
import junit.framework.TestCase.assertFalse
import org.junit.Test

class UrlVerificationTest {

    var hacked = false

    @Test
    fun testThatUrlVerificationFails() {
        val intent = Intent("com.andro.secure.webview")
            .apply { putExtra("url", "http://dodgy.website/") }

        launch<WebViewActivity>(intent)
        Espresso.onView(ViewMatchers.withId(R.id.errorMessage))
            .check(ViewAssertions.matches(ViewMatchers.withText("Error with URL")))
    }

    @Test
    fun testThatBroadcastReceiverDoesNotTrigger() {

        launch(MainActivity::class.java).onActivity {
            it.registerReceiver(
                MaliciousBroadcastReceiver(), IntentFilter.create(
                    "com.andro.secure.sensitive-action",
                    "application/json"
                )
            )

            it.sendBroadcast(Intent("com.andro.secure.sensitive-action"))
        }

        assertFalse("Hacked should be false - we should not have triggered this!!", hacked)
    }

    inner class MaliciousBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            hacked = true
        }

    }
}
