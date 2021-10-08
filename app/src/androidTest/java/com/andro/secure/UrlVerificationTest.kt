package com.andro.secure

import android.content.Intent
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.andro.secure.intents.WebViewActivity
import org.junit.Test

class UrlVerificationTest {

    @Test
    fun testThatUrlVerificationFails() {
        val intent = Intent("com.andro.secure.webview")
            .apply { putExtra("url", "http://dodgy.website/") }

        launch<WebViewActivity>(intent)
        Espresso.onView(ViewMatchers.withId(R.id.errorMessage))
            .check(ViewAssertions.matches(ViewMatchers.withText("Error with URL")))
    }
}
