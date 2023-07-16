package com.safetorun.logger

import com.safetorun.intents.url.logger.withLogger
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

internal class WithLoggerKtTest {

    @Test
    fun `logger function is called when verbose is true`() {
        val logger = mockk<(Boolean, String?) -> Unit>(relaxed = true)
        val invoke: (Int) -> Boolean = { it > 0 }
        val withLoggerFunction = withLogger(verbose = true, logger = logger, invoke = invoke)

        val value = 10
        withLoggerFunction(value)

        verify(exactly = 1) { logger(true, value.toString()) }
    }

    @Test
    fun `logger function is not called when verbose is false`() {
        val logger = mockk<(Boolean, String?) -> Unit>(relaxed = true)
        val invoke: (Int) -> Boolean = { it > 0 }
        val withLoggerFunction = withLogger(verbose = false, logger = logger, invoke = invoke)

        val value = -5
        withLoggerFunction(value)

        verify(exactly = 1) { logger(false, null) }
    }
}
