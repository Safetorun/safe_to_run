package com.safetorun.features.oscheck.builders

import com.google.common.truth.Truth
import com.safetorun.features.oscheck.OSInformationQuery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class BannedBootloaderKtTest : TestCase() {

    private val osInformationQuery = mockk<OSInformationQuery>()
    private val bannedBootloaderCheck = osInformationQuery.bannedBootloader(BANNED_BOOT_LOADER)

    fun `test that when we have a banned bootloader check with a banned bootloader it fails`() {
        // Given
        every { osInformationQuery.bootloader() } returns NOT_BOOTLOADER

        // When Then
        Truth.assertThat(bannedBootloaderCheck().failed).isFalse()
    }

    fun `test that when we have a banned bootloader check without a banned bootloader it passed`() {
        // Given
        every { osInformationQuery.bootloader() } returns BANNED_BOOT_LOADER

        // When Then
        Truth.assertThat(bannedBootloaderCheck().failed).isTrue()
    }

    companion object {
        const val BANNED_BOOT_LOADER = "banned"
        const val NOT_BOOTLOADER = "not_banned"
    }
}
