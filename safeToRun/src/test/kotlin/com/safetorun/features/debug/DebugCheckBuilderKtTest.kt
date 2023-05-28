package com.safetorun.features.debug

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Debug
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase

internal class DebugCheckBuilderKtTest : TestCase() {

    private val context = mockk<Context>()
    private val applicationInfo = ApplicationInfo()

    override fun setUp() {
        every { context.resources } returns AndroidDebuggableStringsSample.setupAMockResources()
        every { context.applicationInfo } returns applicationInfo
        mockkStatic(Debug::class)
        applicationInfo.flags = 0
    }

    fun `test that debugger attached will cause a failure`() {
        mockkStatic(Debug::class)

        every { Debug.isDebuggerConnected() } returns true
        assertTrue(context.banDebugCheck())
    }

    fun `test that debugger connect will cause a failure`() {
        mockkStatic(Debug::class)

        every { Debug.isDebuggerConnected() } returns false
        every { Debug.waitingForDebugger() } returns true

        assertTrue(context.banDebugCheck())
    }

    fun `test that debuggable will cause a failure`() {
        mockkStatic(Debug::class)
        every { Debug.isDebuggerConnected() } returns false
        every { Debug.waitingForDebugger() } returns false
        applicationInfo.flags = ApplicationInfo.FLAG_DEBUGGABLE
        assertTrue(context.banDebugCheck())
    }

    fun `test that not debuggable and no debugger will cause a pass`() {
        mockkStatic(Debug::class)

        every { Debug.isDebuggerConnected() } returns false
        every { Debug.waitingForDebugger() } returns false

        assertFalse(context.banDebugCheck())
    }
}
