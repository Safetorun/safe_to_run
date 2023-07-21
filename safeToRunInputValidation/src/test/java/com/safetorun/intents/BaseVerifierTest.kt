package com.safetorun.intents

import io.mockk.every
import io.mockk.mockk
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
        val nextFunctionMock = mockk<(Boolean, Int) -> Boolean>(relaxed = true)
        baseVerifier.andThen(nextFunctionMock)

        baseVerifier.verify(42)

        // Verify that the next function is invoked with the expected arguments
        val expectedArg1 = true
        val expectedArg2 = 42
        every { nextFunctionMock.invoke(any(), any()) } returns false
        baseVerifier.verify(42)
        io.mockk.verify { nextFunctionMock.invoke(expectedArg1, expectedArg2) }
    }

    @Test
    fun testVerifyWithNextFunctionReturningTrue() {
        val nextFunctionMock = mockk<(Boolean, Int) -> Boolean>(relaxed = true)
        baseVerifier.andThen(nextFunctionMock)

        baseVerifier.verify(42)

        io.mockk.verify { nextFunctionMock.invoke(any(), any()) }
    }

    @Test
    fun testVerifyWithNextFunctionReturningFalse() {
        val nextFunctionMock = mockk<(Boolean, Int) -> Boolean>(relaxed = true)
        baseVerifier.andThen(nextFunctionMock)

        // Next function returning false will break the verification chain.
        // The result should be false, and the next function should not be invoked.
        val result = baseVerifier.verify(-10)

        assertFalse(result)
        io.mockk.verify(exactly = 0) { nextFunctionMock.invoke(any(), any()) }
    }
}
