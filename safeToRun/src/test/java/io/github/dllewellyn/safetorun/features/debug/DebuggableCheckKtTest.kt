package io.github.dllewellyn.safetorun.features.debug

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Debug
import io.github.dllewellyn.safetorun.features.debug.AndroidDebuggableStringsSample.mapReportsToMessages
import io.github.dllewellyn.safetorun.features.debug.AndroidDebuggableStringsSample.setupAMockResources
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase

internal class DebuggableCheckKtTest : TestCase() {

    private val context = mockk<Context>()
    private val check = context.debugCheck()
    private val applicationInfo = ApplicationInfo()

    override fun setUp() {
        every { context.resources } returns setupAMockResources()
        every { context.applicationInfo } returns applicationInfo
        mockkStatic(Debug::class)
    }

    fun `test that when we call debuggable and debug is false we pass`() {
        // Given
        applicationInfo.flags = 0
        every { Debug.isDebuggerConnected() } returns false
        every { Debug.waitingForDebugger() } returns false

        // When
        val result = check.canRun() as SafeToRunReport.MultipleReports

        // Then
        Truth.assertThat(result.reports.map(::mapReportsToMessages).flatten().toList())
            .containsExactly(
                AndroidDebuggableStringsSample.NOT_DEBUGGABLE,
                AndroidDebuggableStringsSample.DEBUGGER_NOT_ATTACHED
            )
    }

    fun `test that when we call debuggable and debug is true we fail`() {
        // Given
        applicationInfo.flags = ApplicationInfo.FLAG_DEBUGGABLE
        every { Debug.isDebuggerConnected() } returns false
        every { Debug.waitingForDebugger() } returns false

        // When
        val result = check.canRun() as SafeToRunReport.MultipleReports

        // Then
        Truth.assertThat(result.reports.map(::mapReportsToMessages).flatten())
            .isEqualTo(
                listOf(
                    AndroidDebuggableStringsSample.DEBUGGABLE,
                    AndroidDebuggableStringsSample.DEBUGGER_NOT_ATTACHED
                )
            )
    }

    fun `test that when we call debuggable and both are true we fail`() {
        // Given
        applicationInfo.flags = ApplicationInfo.FLAG_DEBUGGABLE
        every { Debug.isDebuggerConnected() } returns false
        every { Debug.waitingForDebugger() } returns true

        // When
        val result = check.canRun() as SafeToRunReport.MultipleReports

        // Then
        Truth.assertThat(result.reports.map(::mapReportsToMessages).flatten())
            .isEqualTo(
                listOf(
                    AndroidDebuggableStringsSample.DEBUGGABLE,
                    AndroidDebuggableStringsSample.DEBUGGER_ATTACHED
                )
            )
    }

    fun `test that when we call debuggable one is true and false we fail`() {
        // Given
        applicationInfo.flags = 0
        every { Debug.isDebuggerConnected() } returns true
        every { Debug.waitingForDebugger() } returns false

        // When
        val result = check.canRun() as SafeToRunReport.MultipleReports

        // Then
        Truth.assertThat(result.reports.map(::mapReportsToMessages).flatten())
            .isEqualTo(
                listOf(
                    AndroidDebuggableStringsSample.NOT_DEBUGGABLE,
                    AndroidDebuggableStringsSample.DEBUGGER_ATTACHED
                )
            )
    }
}
