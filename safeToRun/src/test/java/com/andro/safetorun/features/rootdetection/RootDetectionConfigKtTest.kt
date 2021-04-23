package com.andro.safetorun.features.rootdetection

import android.content.Context
import com.andro.safetorun.reporting.SafeToRunReport
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import junit.framework.TestCase

internal class RootDetectionConfigKtTest : TestCase() {


    fun `test that root detection config does not return null`() {
        val context = mockk<Context>(relaxed = true)

        val result = context.rootDetection {
            tolerateBusyBox = true
            tolerateRoot = true
        }.canRun() as SafeToRunReport.SafeToRunReportSuccess

        assertThat(result.successMessage).isNotNull()
    }

}