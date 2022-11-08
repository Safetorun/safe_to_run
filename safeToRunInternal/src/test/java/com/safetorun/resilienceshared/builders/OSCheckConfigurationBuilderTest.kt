package com.safetorun.resilienceshared.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.resilienceshared.dto.CheckComparator
import com.safetorun.resilienceshared.dto.IntCheckType
import com.safetorun.resilienceshared.dto.SingleIntCheck
import org.junit.Test

internal class OSCheckConfigurationBuilderTest {


    @Test
    fun `test that os check configuration builder can add a single check`() {
        val check = OSCheckConfigurationBuilder()
            .apply {
                singleCheck {
                    IntCheckType.MinOsCheck.failIf(30, CheckComparator.GREATER_THAN)
                }
            }
            .build()

        val singleCheck = check.configuration.first().allIntChecks.first()
        assertThat(singleCheck.checkType).isEqualTo(IntCheckType.MinOsCheck)
        assertThat(singleCheck.comparator).isEqualTo(CheckComparator.GREATER_THAN)
        assertThat(singleCheck.intValue).isEqualTo(30)
    }
}
