package io.github.dllewellyn.safetorun.features.oscheck.emulators

import com.google.common.truth.Truth
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.github.dllewellyn.safetorun.features.oscheck.OsCheckConstants
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class GenymotionEmulatorKtTest : TestCase() {
    private val osInformation = mockk<OSInformationQuery>()

    fun `test that genymotion emulator works as correctly if board is unknown`() {
        // Given
        every { osInformation.bootloader() } returns NOT_BANNED
        every { osInformation.manufacturer() } returns NOT_BANNED
        every { osInformation.board() } returns OsCheckConstants.UNKNOWN

        // When Then
        Truth.assertThat(osInformation.banGenymotionEmulator()().failed).isTrue()
    }

    fun `test that genymotion emulator works as correctly if bootloader is banned`() {
        // Given
        every { osInformation.board() } returns NOT_BANNED
        every { osInformation.manufacturer() } returns NOT_BANNED
        every { osInformation.bootloader() } returns OsCheckConstants.UNKNOWN

        // When Then
        Truth.assertThat(osInformation.banGenymotionEmulator()().failed).isTrue()
    }

    fun `test that genymotion emulator works as correctly if board is banned`() {
        // Given
        every { osInformation.bootloader() } returns NOT_BANNED
        every { osInformation.manufacturer() } returns OsCheckConstants.GENYMOTION_MANUFACTURER
        every { osInformation.board() } returns NOT_BANNED

        // When Then
        Truth.assertThat(osInformation.banGenymotionEmulator()().failed).isTrue()
    }

    fun `test that genymotion emulator works as correctly if nothing is banned`() {
        // Given
        every { osInformation.bootloader() } returns NOT_BANNED
        every { osInformation.manufacturer() } returns NOT_BANNED
        every { osInformation.board() } returns NOT_BANNED

        // When Then
        Truth.assertThat(osInformation.banGenymotionEmulator()().failed).isFalse()
    }

    companion object {
        const val NOT_BANNED = "Not banned"
    }
}
