package io.github.dllewellyn.safetorun.pinscreen

import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.pinscreen.models.Attempts
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class SafeToRunStorageTest {

    private val storage by lazy { safeToRunPinStorage(InstrumentationRegistry.getInstrumentation().context) }

    @Before
    fun beforeEach() {
        runBlocking {
            storage.clear()
        }
    }

    @Test
    fun test_that_safe_to_run_storage_can_store_a_pin_and_retrieve_it() {
        val pin = "123"
        runTest {
            assertThat(storage.retrievePin()).isNull()
            storage.savePin(pin)
            assertThat(storage.retrievePin()).isEqualTo(pin)
            storage.clear()
            assertThat(storage.retrievePin()).isNull()
        }
    }

    @Test
    fun test_that_safe_to_run_storage_can_log_attempts() {
        val attempts = Attempts(3)

        runTest {
            assertThat(storage.getAttempts()).isNull()
            storage.logAttempt(attempts)
            assertThat(storage.getAttempts()).isEqualTo(attempts)
            storage.clear()
            assertThat(storage.getAttempts()).isNull()
        }
    }

}