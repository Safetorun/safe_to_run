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
        every { osInformation.bootloader() } returns "Not banned"
        every { osInformation.manufacturer() } returns "Not banned"
        every { osInformation.board() } returns OsCheckConstants.UNKNOWN

        // When Then
        Truth.assertThat(osInformation.banGenymotionEmulator()().failed).isTrue()
    }

    fun `test that genymotion emulator works as correctly if bootloader is banned`() {
        // Given
        every { osInformation.board() } returns "Not banned"
        every { osInformation.manufacturer() } returns "Not banned"
        every { osInformation.bootloader() } returns OsCheckConstants.UNKNOWN

        // When Then
        Truth.assertThat(osInformation.banGenymotionEmulator()().failed).isTrue()
    }

    fun `test that genymotion emulator works as correctly if board is banned`() {
        // Given
        every { osInformation.bootloader() } returns "Not banned"
        every { osInformation.manufacturer() } returns OsCheckConstants.GENYMOTION_MANUFACTURER
        every { osInformation.board() } returns "Not banned"

        // When Then
        Truth.assertThat(osInformation.banGenymotionEmulator()().failed).isTrue()
    }

    fun `test that genymotion emulator works as correctly if nothing is banned`() {
        // Given
        every { osInformation.bootloader() } returns "Not banned"
        every { osInformation.manufacturer() } returns "Not banned"
        every { osInformation.board() } returns "Not banned"

        // When Then
        Truth.assertThat(osInformation.banGenymotionEmulator()().failed).isFalse()
    }
}
