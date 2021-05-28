package io.github.dllewellyn.safetorun.repository

import android.content.Context
import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.offdevice.FakeSharedPrefs
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import org.junit.Test

class AndroidDeviceIdRepositoryTest : TestCase() {

    private val context = mockk<Context>().apply {
        every { getSharedPreferences(any(), any()) } returns FakeSharedPrefs()
    }

    @Test
    fun `test that calling device twice comes back with the same ID`() {
        // Given
        val repository = AndroidDeviceIdRepository(context)

        // When // Then
        assertThat(repository.getOrCreateDeviceIdSync()).isEqualTo(repository.getOrCreateDeviceIdSync())
    }

    fun `test that calling device twice comes back with the same ID async`() {
        // Given
        val repository = AndroidDeviceIdRepository(context)

        // When // Then
        repository.getOrCreateDeviceIdAsync { firstString ->
            repository.getOrCreateDeviceIdAsync { secondString ->
                assertThat(firstString).isEqualTo(secondString)
            }
        }
    }
}
