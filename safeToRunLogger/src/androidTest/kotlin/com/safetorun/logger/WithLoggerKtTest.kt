import com.safetorun.intents.SafeToRunVerifier
import com.safetorun.logger.models.VerifyType
import com.safetorun.logger.withLogger
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class WithLoggerKtTest {

    private lateinit var safeToRunVerifier: SafeToRunVerifier<Int>
    private lateinit var logger: (Boolean, VerifyType, String?) -> Unit

    @Before
    fun setUp() {
        // Create a mock logger for testing
        logger = mockk(relaxed = true)

        // Create a mock SafeToRunVerifier
        safeToRunVerifier = object : SafeToRunVerifier<Int> {

            val nextList: MutableList<(Boolean, Int) -> Unit> = mutableListOf()

            override fun verify(input: Int): Boolean {
                return (input >= 0).also {
                    nextList.forEach { next -> next.invoke(it, input) }
                }
            }

            override fun andThen(next: (Boolean, Int) -> Unit): SafeToRunVerifier<Int> {
                nextList.add(next)
                return this
            }
        }
    }

    @Test
    fun `test withLogger logs value when logValue is true`() {
        // Create the wrapped SafeToRunVerifier with logger
        val wrappedVerifier = safeToRunVerifier.withLogger(logValue = true, logger = logger)

        // Perform verification
        val result = wrappedVerifier.verify(42)

        // Verify that the logger was called with the appropriate arguments
        verify(exactly = 1) { logger.invoke(result, VerifyType.Url, "42") }
    }

    @Test
    fun `test withLogger does not log value when logValue is false`() {
        // Create the wrapped SafeToRunVerifier with logger
        val wrappedVerifier = safeToRunVerifier.withLogger(logValue = false, logger = logger)

        // Perform verification
        val result = wrappedVerifier.verify(42)

        // Verify that the logger was called with the appropriate arguments
        verify(exactly = 1) { logger.invoke(result, VerifyType.Url, null) }
    }

    @Test
    fun `test withLogger returns the same SafeToRunVerifier instance`() {
        // Create the wrapped SafeToRunVerifier with logger
        val wrappedVerifier = safeToRunVerifier.withLogger(logValue = true, logger = logger)

        // Ensure that the returned instance is the same as the original SafeToRunVerifier
        assertEquals(safeToRunVerifier, wrappedVerifier)
    }
}
