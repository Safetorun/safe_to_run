package com.andro.secure

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.andro.safetorun.SafeToRun
import com.andro.safetorun.configure
import com.andro.safetorun.features.blacklistedapps.blacklistConfiguration
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test


class OSConfigurationTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testThatWeCanDoASimpleOsConfigurationTest() {
        activityRule.scenario.onActivity { activity ->
            SafeToRun.init(
                configure {
                    plus {
                        activity.blacklistConfiguration {
                            +"com.abc.def"
                        }
                    }
                }
            )
            assertThat(SafeToRun.isSafeToRun(activity)).isTrue()
        }
    }

    @Test
    fun testThatWeCanDoASimpleOsConfigurationTestThatFails() {
        activityRule.scenario.onActivity { activity ->
            SafeToRun.init(
                configure {
                    plus {
                        activity.blacklistConfiguration {
                            + activity.packageName
                        }
                    }
                }
            )
            assertThat(SafeToRun.isSafeToRun(activity)).isFalse()
        }

    }
}