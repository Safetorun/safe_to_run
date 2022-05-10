package com.safetorun.features.oscheck

import android.content.Context
import android.content.res.Resources
import com.safetorun.R
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class AndroidOSDetectionStringsTest : TestCase() {

    private val resources = mockk<Resources>()
    private val context = mockk<Context>()

    override fun setUp() {
        every { context.resources } returns resources
        every { resources.getString(R.string.os_check_failed) } returns FAILED
        every { resources.getString(R.string.os_check_passed) } returns PASSED
    }

    fun `test that android default os calls through to resources`() {
        with(AndroidOSDetectionStrings(context)) {
            assertThat(genericFailureMessage()).isEqualTo(FAILED)
            assertThat(genericPassMessage()).isEqualTo(PASSED)
        }
    }

    companion object {
        const val PASSED = "passed"
        const val FAILED = "failed"
    }
}
