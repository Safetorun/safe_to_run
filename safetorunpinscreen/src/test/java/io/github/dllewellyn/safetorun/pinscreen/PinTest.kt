package io.github.dllewellyn.safetorun.pinscreen

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.pinscreen.models.Attempts
import io.github.dllewellyn.safetorun.pinscreen.models.HashedPin
import io.github.dllewellyn.safetorun.pinscreen.models.MaxAttemptsBehaviour
import io.github.dllewellyn.safetorun.pinscreen.models.PinCheckResult
import io.github.dllewellyn.safetorun.pinscreen.models.RetryStrategy
import io.github.dllewellyn.safetorun.pinscreen.storage.AttemptsLogger
import io.github.dllewellyn.safetorun.pinscreen.storage.PinStorage
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
internal class PinTest {
    private val numberOfAttempts = 3
    private val backOffTime = 100L
    private val pin = "1234"

    private val prePinHasher: suspend (HashedPin) -> String = { (pin, _) -> pin }
    private val logAttempts = mockk<suspend (Attempts) -> Unit>(relaxed = true)
    private val retrieveAttempts = mockk<suspend () -> Attempts>()
    private val storePin = mockk<suspend (String) -> Unit>(relaxed = true)

    private fun pinStorage(getterFn: () -> String?) = object : PinStorage {
        override suspend fun clear() {
            // Nothing to do
        }

        override suspend fun savePin(pin: String) {
            this@PinTest.storePin(pin)
        }

        override suspend fun retrievePin(): String? {
            return getterFn()
        }

        override suspend fun retrieveOrCreateSalt(): String {
            return "SALT"
        }
    }

    @Test
    fun `test that we are able to store a pin`() {
        runTest {
            // Given When
            setPin(pin, pinStorage { "" }, prePinHasher)

            // Then
            coVerify { storePin(pin) }
        }
    }

    @Test
    fun `test that if the storage returns no pin then does have pin returns false`() {
        runTest {
            assertThat(haveSetPin(pinStorage = pinStorage { null })).isFalse()
        }
    }

    @Test
    fun `test that if the storage returns a pin then does have pin returns true`() {
        runTest {
            assertThat(haveSetPin(pinStorage = pinStorage { "abc" })).isTrue()
        }
    }

    @Test
    fun `test that validate without a pin causes not set error`() {
        runTest {
            assertThat(
                validatePinCommand({ null })
            ).isEqualTo(
                PinCheckResult.PinCheckError.NoPinSet
            )
        }
    }

    @Test
    fun `test that validate pin returns a positive result if correct pin`() {
        runTest {
            // Given

            coEvery { retrieveAttempts() } coAnswers { Attempts(1, Date()) }

            assertThat(
                validatePinCommand({ pin })
            ).isEqualTo(PinCheckResult.PinAccepted)

            verifyExpectedAttempts(0)
        }
    }

    @Test
    fun `test validate pin returns a result if correct pin retry has elapsed, permanent lockout`() {
        runTest {
            // Given
            val retryStrategy = retryStrategy {
                attemptsBeforeLockout = 3
                maxAttemptsBehaviour = MaxAttemptsBehaviour.PermanentLockout
            }

            coEvery { retrieveAttempts() } coAnswers { Attempts(3, Date()) }

            assertThat(
                validatePinCommand({ pin }, retryStrategy)
            ).isEqualTo(PinCheckResult.PinCheckError.PermanentLockout(3))
        }
    }

    @Test
    fun `test  validate pin returns correct pin but retry has elapsed and non permanent lockout`() {
        runTest {
            // Given
            val retryStrategy = retryStrategy {
                attemptsBeforeLockout = 3
                maxAttemptsBehaviour = MaxAttemptsBehaviour.ExponentialBackoff(backOffTime)
            }

            coEvery { retrieveAttempts() } coAnswers { Attempts(numberOfAttempts, Date()) }

            assertThat(
                validatePinCommand({ pin }, retryStrategy)
            ).isEqualTo(PinCheckResult.PinCheckError.TooManyAttempts(numberOfAttempts, backOffTime))
        }
    }

    @Test
    fun `test that validate pin returns a incorrect value when incorrect pin entered`() {
        runTest {
            // Given
            val retryStrategy = retryStrategy {
                attemptsBeforeLockout = 3
            }

            coEvery { retrieveAttempts() } coAnswers { Attempts(1) }

            assertThat(
                validatePinCommand({ NOT_PIN }, retryStrategy)
            ).isEqualTo(PinCheckResult.PinCheckError.PinIncorrect(1))

            verifyExpectedAttempts(2)
        }
    }

    @Test
    fun `test that validate pin returns a wrong pin error if you have not maxed your attempts`() {
        runTest {
            // Given
            val retryStrategy = retryStrategy {
                attemptsBeforeLockout = 3
                maxAttemptsBehaviour = MaxAttemptsBehaviour.PermanentLockout
            }

            coEvery { retrieveAttempts() } coAnswers { Attempts(1) }

            // When Then
            assertThat(
                validatePinCommand({ NOT_PIN }, retryStrategy)
            ).isEqualTo(PinCheckResult.PinCheckError.PinIncorrect(1))
            verifyExpectedAttempts(2)
        }


    }


    @Test
    fun `test that validate pin returns an permanent error if you have maxed your attempts`() {
        runTest {
            // Given
            val retryStrategy = retryStrategy {
                attemptsBeforeLockout = 2
                maxAttemptsBehaviour = MaxAttemptsBehaviour.PermanentLockout
            }

            coEvery { retrieveAttempts() } coAnswers { Attempts(1, Date()) }

            // When Then
            assertThat(
                validatePinCommand({ NOT_PIN }, retryStrategy)
            ).isEqualTo(PinCheckResult.PinCheckError.PermanentLockout(2))
        }


    }

    @Test
    fun `test that validate pin returns a back off time if that is the behaviour`() {
        runTest {
            // Given
            val backoffBehaviour = MaxAttemptsBehaviour.ExponentialBackoff(backOffTime)
            val retryStrategy = retryStrategy {
                attemptsBeforeLockout = 2
                maxAttemptsBehaviour = backoffBehaviour
            }

            coEvery { retrieveAttempts() } coAnswers { Attempts(1, Date()) }

            // When Then
            assertThat(
                validatePinCommand({ NOT_PIN }, retryStrategy)
            ).isEqualTo(
                PinCheckResult.PinCheckError.TooManyAttempts(
                    2,
                    backoffBehaviour.backOffTime
                )
            )
        }
    }

    private fun verifyExpectedAttempts(
        expectedAttempts: Int
    ) {
        coVerify { logAttempts(match { it.attempts == expectedAttempts }) }
    }

    private suspend fun validatePinCommand(
        getPinFn: () -> String?,
        retryStrategy: RetryStrategy = retryStrategy { }
    ) = validatePin(
        pin,
        retryStrategy,
        pinStorage { getPinFn() },
        prePinHasher,
        object : AttemptsLogger {
            override suspend fun logAttempt(attempts: Attempts) {
                logAttempts(attempts)

            }

            override suspend fun getAttempts(): Attempts {
                return retrieveAttempts()
            }

        }
    )

    companion object {
        const val NOT_PIN = "not pin"
    }
}
