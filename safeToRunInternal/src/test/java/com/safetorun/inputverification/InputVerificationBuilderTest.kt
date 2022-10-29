package com.safetorun.inputverification

import com.google.common.truth.Truth.assertThat
import com.safetorun.safeToRun
import org.junit.Test


internal class InputVerificationBuilderTest {

    @Test
    fun `test that input verification builder can build a configuration with allow all urls`() {
        val configurationName = "test name"
        val str = safeToRun {
            inputVerification {
                urlConfiguration(configurationName) {
                    allowAnyParameter = true
                    allowAnyUrl = false
                }
            }
        }

        val configuration =
            str.inputVerification?.urlConfigurations?.first() ?: throw IllegalAccessError()
        assertThat(configuration.name).isEqualTo(configurationName)
        assertThat(configuration.configuration.allowAnyParameter).isTrue()
        assertThat(configuration.configuration.allowAnyUrl).isFalse()

    }
}
