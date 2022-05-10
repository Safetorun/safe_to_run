package com.safetorun.features.debug

import android.content.Context
import com.safetorun.features.debug.AndroidDebuggableStringsSample.DEBUGGABLE
import com.safetorun.features.debug.AndroidDebuggableStringsSample.DEBUGGER_ATTACHED
import com.safetorun.features.debug.AndroidDebuggableStringsSample.DEBUGGER_NOT_ATTACHED
import com.safetorun.features.debug.AndroidDebuggableStringsSample.NOT_DEBUGGABLE
import com.safetorun.features.debug.AndroidDebuggableStringsSample.setupAMockResources
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class AndroidDebuggableStringsTest : TestCase() {

    private val context = mockk<Context>()

    override fun setUp() {
        every { context.resources } returns setupAMockResources()
    }

    fun `test messages are as expected`() {
        with(AndroidDebuggableStrings(context)) {
            assertThat(appIsDebuggableMessage()).isEqualTo(DEBUGGABLE)
            assertThat(appIsNotDebuggableMessage()).isEqualTo(NOT_DEBUGGABLE)
            assertThat(debuggerAttachedMessage()).isEqualTo(DEBUGGER_ATTACHED)
            assertThat(debuggerNotAttachedMessage()).isEqualTo(DEBUGGER_NOT_ATTACHED)
        }
    }
}
