import com.safetorun.intents.BaseVerifier
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BaseVerifierTest {

    private lateinit var baseVerifier: BaseVerifier<Int>

    @Before
    fun setUp() {
        baseVerifier = object : BaseVerifier<Int>() {
            override fun internalVerify(input: Int): Boolean {
                // Add your verification logic for the test here
                return input >= 0
            }
        }
    }

    @Test
    fun testInternalVerifyWithValidInput() {
        val result = baseVerifier.internalVerify(42)
        assertTrue(result)
    }

    @Test
    fun testInternalVerifyWithInvalidInput() {
        val result = baseVerifier.internalVerify(-10)
        assertFalse(result)
    }

    @Test
    fun testAndThen() {
        val nextFunctionMock = mockk<(Boolean, Int) -> Unit>(relaxed = true)
        baseVerifier.andThen(nextFunctionMock)

        baseVerifier.verify(42)

        // Verify that the next function is invoked with the expected arguments
        val expectedArg1 = true
        val expectedArg2 = 42
        verify(exactly = 1) { nextFunctionMock.invoke(expectedArg1, expectedArg2) }
    }

    @Test
    fun testVerifyWithNextFunctionReturningTrue() {
        val nextFunctionMock = mockk<(Boolean, Int) -> Unit>(relaxed = true)
        baseVerifier.andThen(nextFunctionMock)

        baseVerifier.verify(42)

        verify(exactly = 1) { nextFunctionMock.invoke(true, 42) }
    }

    @Test
    fun testVerifyWithNextFunctionReturningTrueTwoFuncs() {
        val nextFunctionMock = mockk<(Boolean, Int) -> Unit>(relaxed = true)
        val nextFunctionMock2 = mockk<(Boolean, Int) -> Unit>(relaxed = true)

        baseVerifier.andThen(nextFunctionMock)
        baseVerifier.andThen(nextFunctionMock2)

        baseVerifier.verify(42)

        verify(exactly = 1) { nextFunctionMock.invoke(true, 42) }
        verify(exactly = 1) { nextFunctionMock2.invoke(true, 42) }
    }
}
