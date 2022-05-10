package com.safetorun.pinscreen

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.pinscreen.SafeToRunPinScreen
import io.github.dllewellyn.safetorun.pinscreen.retryStrategy
import io.github.dllewellyn.safetorun.pinscreen.safeToRunPinScreen
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
internal class SafeToRunPinScreenKtTest {

    private val storage = mockk<SafeToRunStorage>()

    @Test
    fun `test that safe to run builder returns a builder`() {
        assertThat(safeToRunPinScreen(mockk()) {
            retryStrategy = retryStrategy {
                attemptsBeforeLockout = 1
            }
        }).isNotNull()
    }

    @Test
    fun `test that safe to run pin screen allows you to check if pin exists when it doesnt`() {
        // Given
        val pinScreen = SafeToRunPinScreen(storage, retryStrategy {  })

        // When
        coEvery { storage.retrievePin() } returns null

        // Then
        runTest {
            assertThat(pinScreen.isPinSet()).isFalse()
        }
    }

    @Test
    fun `test that safe to run pin screen allows you to check if pin exists when it does`() {
        // Given
        val pinScreen = SafeToRunPinScreen(storage, retryStrategy {  })

        // When
        coEvery { storage.retrievePin() } returns PIN

        // Then
        runTest {
            assertThat(pinScreen.isPinSet()).isTrue()
        }
    }

    companion object {
        const val PIN = "pin123"
    }
}
