import com.safetorun.intents.logger.withLogger
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class WithLoggerKtTest {

    @Test
    fun `logger function is called when verbose is true`() {
        val logger = mockk<(Boolean, String?) -> Unit>(relaxed = true)
        val withLoggerFunction = withLogger<Int>(logValue = true, logger = logger) {
            it > 0
        }

        val value = 10
        val result = withLoggerFunction(value)

        assertEquals(true, result)
        verify(exactly = 1) { logger(true, value.toString()) }
    }

    @Test
    fun `logger function is not called when verbose is false`() {
        val logger = mockk<(Boolean, String?) -> Unit>(relaxed = true)
        val withLoggerFunction = withLogger<Int>(logger = logger) {
            it > 0
        }

        val value = -5
        val result = withLoggerFunction(value)

        assertEquals(false, result)
        verify(exactly = 1) { logger(false, null) }
    }

    @Test
    fun `logger function is called with null when logValue is true and value is null`() {
        val logger = mockk<(Boolean, String?) -> Unit>(relaxed = true)
        val withLoggerFunction = withLogger<String?>(logValue = true, logger = logger) {
            it != null
        }

        val value: String? = null
        val result = withLoggerFunction(value)

        assertEquals(false, result)
        verify(exactly = 1) { logger(false, null) }
    }

    @Test
    fun `logger function is called with custom message when logValue is true`() {
        val logger = mockk<(Boolean, String?) -> Unit>(relaxed = true)
        val withLoggerFunction = withLogger<String>(logValue = true, logger = logger) {
            it.length > 5
        }

        val value = "Hello World"
        val result = withLoggerFunction(value)

        assertEquals(true, result)
        verify(exactly = 1) { logger(true, value) }
    }
}
