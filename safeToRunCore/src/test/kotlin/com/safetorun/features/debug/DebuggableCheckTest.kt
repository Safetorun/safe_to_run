package com.safetorun.features.debug

import com.google.common.truth.Truth.assertThat
import com.safetorun.features.debug.SharedFunctions.DEBUGGABLE
import com.safetorun.features.debug.SharedFunctions.DEBUGGER_ATTACHED
import com.safetorun.features.debug.SharedFunctions.DEBUGGER_NOT_ATTACHED
import com.safetorun.features.debug.SharedFunctions.NOT_DEBUGGABLE
import com.safetorun.features.debug.SharedFunctions.mapReportsToMessages
import com.safetorun.reporting.SafeToRunReport
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class DebuggableCheckTest : TestCase() {

    private val debuggable = mockk<IsDebuggable>()
    private val debuggableStrings = mockk<DebuggableStrings>()

    private val check = debugCheckConfiguration(debuggable, debuggableStrings)

    override fun setUp() {
        every { debuggableStrings.appIsDebuggableMessage() } returns DEBUGGABLE
        every { debuggableStrings.appIsNotDebuggableMessage() } returns NOT_DEBUGGABLE
        every { debuggableStrings.debuggerAttachedMessage() } returns DEBUGGER_ATTACHED
        every { debuggableStrings.debuggerNotAttachedMessage() } returns DEBUGGER_NOT_ATTACHED
    }

    fun `test that when we call debuggable and debug is false we pass`() {
        // Given
        every { debuggable.isDebuggable() } returns false
        every { debuggable.isDebuggerAttached() } returns false

        // When
        val result = check.canRun() as SafeToRunReport.MultipleReports

        // Then
        assertThat(result.reports.map(::mapReportsToMessages).flatten().toList())
            .containsExactly(NOT_DEBUGGABLE, DEBUGGER_NOT_ATTACHED)
    }

    fun `test that when we call debuggable and debug is true we fail`() {
        // Given
        every { debuggable.isDebuggable() } returns true
        every { debuggable.isDebuggerAttached() } returns false

        // When
        val result = check.canRun() as SafeToRunReport.MultipleReports

        // Then
        assertThat(result.reports.map(::mapReportsToMessages).flatten())
            .isEqualTo(listOf(DEBUGGABLE, DEBUGGER_NOT_ATTACHED))
    }

    fun `test that when we call debuggable and both are true we fail`() {
        // Given
        every { debuggable.isDebuggable() } returns true
        every { debuggable.isDebuggerAttached() } returns true

        // When
        val result = check.canRun() as SafeToRunReport.MultipleReports

        // Then
        assertThat(result.reports.map(::mapReportsToMessages).flatten())
            .isEqualTo(listOf(DEBUGGABLE, DEBUGGER_ATTACHED))
    }

    fun `test that when we call debuggable one is true and false we fail`() {
        // Given
        every { debuggable.isDebuggable() } returns false
        every { debuggable.isDebuggerAttached() } returns true

        // When
        val result = check.canRun() as SafeToRunReport.MultipleReports

        // Then
        assertThat(result.reports.map(::mapReportsToMessages).flatten())
            .isEqualTo(listOf(NOT_DEBUGGABLE, DEBUGGER_ATTACHED))
    }
}
