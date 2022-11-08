package com.safetorun.resilienceshared.builders

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class RootCheckConfigurationBuilderTest {

    @Test
    fun `test that by default builder doesnt allow busybox`() {
        assertThat(RootCheckConfigurationBuilder().build().tolerateBusyBox).isFalse()
    }

    @Test
    fun `test that we can tolerate busybox`() {
        assertThat(RootCheckConfigurationBuilder()
            .apply { tolerateBusyBox = true }
            .build().tolerateBusyBox).isTrue()
    }
}
