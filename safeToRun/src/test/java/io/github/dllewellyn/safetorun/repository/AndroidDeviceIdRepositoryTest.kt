package io.github.dllewellyn.safetorun.repository

import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AndroidDeviceIdRepositoryTest : TestCase() {

    @Test
    fun `test that calling device twice comes back with the same ID`() {
        // Given
        val repository = AndroidDeviceIdRepository(ApplicationProvider.getApplicationContext())

        // When // Then
        assertThat(repository.getOrCreateDeviceIdSync()).isEqualTo(repository.getOrCreateDeviceIdSync())
    }

    fun `test that calling device twice comes back with the same ID async`() {
        // Given
        val repository = AndroidDeviceIdRepository(ApplicationProvider.getApplicationContext())

        // When // Then
        repository.getOrCreateDeviceIdAsync { firstString ->
            repository.getOrCreateDeviceIdAsync { secondString ->
                assertThat(firstString).isEqualTo(secondString)
            }
        }
    }
}
