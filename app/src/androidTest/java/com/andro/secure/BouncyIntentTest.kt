package com.andro.secure

import android.content.Intent
import android.util.Log
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.andro.secure.intents.BouncableActivity
import org.junit.Test

class BouncyIntentTest {

    @Test
    fun testThatBouncyIntentsDontAllowAccessToProtectedComponents() {
        launch<BouncableActivity>(setupBouncingIntentButton())
            .apply {
                onActivity {
                    Log.v("Activity", "Act")
                }
            }

        onView(withId(R.id.protectedText)).check(ViewAssertions.doesNotExist())
        onView(withId(R.id.oopsNotWorking)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.oops_couldn_t_bounce)))
    }

    private fun setupBouncingIntentButton() =
        Intent("com.andro.secure.bouncy").apply {
            putExtra("bounce", buildBouncyIntent())
        }


    private fun buildBouncyIntent() = Intent("com.andro.secure.sensitive-activity")
}