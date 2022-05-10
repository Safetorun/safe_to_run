package com.andro.secure

import androidx.test.ext.junit.rules.ActivityScenarioRule
import io.github.dllewellyn.safetorun.SafeToRun
import io.github.dllewellyn.safetorun.configure
import com.safetorun.features.blacklistedapps.blacklistConfiguration
import org.junit.Rule
import org.junit.Test

class OSConfigurationTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testThatWeCanDoASimpleOsConfigurationTest() {
        activityRule.scenario.onActivity { activity ->
            SafeToRun.init {
                configure {
                    activity.blacklistConfiguration {
                        +"com.abc.def"
                    }.error()
                }
            }
        }
    }

    @Test
    fun testThatWeCanDoASimpleOsConfigurationTestThatFails() {
        activityRule.scenario.onActivity { activity ->
            SafeToRun.init {
                configure {
                    activity.blacklistConfiguration {
                        +activity.packageName
                    }.warn()
                }
            }
        }
    }
}
