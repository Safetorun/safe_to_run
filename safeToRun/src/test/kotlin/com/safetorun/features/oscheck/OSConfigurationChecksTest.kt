package com.safetorun.features.oscheck

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class OSConfigurationChecksTest : TestCase() {

    private val osCheck by lazy { mockk<OSInformationQuery>() }

    override fun setUp() {
        every { osCheck.osVersion() } returns CURRENT_SDK
        every { osCheck.manufacturer() } returns CURRENT_MANUFACTURER
        every { osCheck.model() } returns CURRENT_MODEL
        every { osCheck.board() } returns CURRENT_BOARD
        every { osCheck.bootloader() } returns CURRENT_BOOTLOADER
        every { osCheck.cpuAbi() } returns CURRENT_ABIS.toList()
        every { osCheck.hardware() } returns CURRENT_HARDWARE
        every { osCheck.device() } returns CURRENT_DEVICE
        every { osCheck.host() } returns CURRENT_HOST

    }

    fun `test that min os version check passes if os version is greater than min`() {
        assertThat(minOsVersionCheck(CURRENT_SDK - 1, osCheck)).isFalse()
    }

    fun `test that min os version check passes if os version is equal than min`() {
        assertThat(minOsVersionCheck(CURRENT_SDK, osCheck)).isFalse()
    }

    fun `test that min os version check fails if os version is less than min`() {
        assertThat(minOsVersionCheck(CURRENT_SDK + 1, osCheck)).isTrue()
    }

    fun `test that manufacturer check passes if we are not a banned manufacturer`() {
        assertThat(notManufacturerCheck("Not banned", osCheck)).isFalse()
    }

    fun `test that manufacturer check fails if we are a banned manufacturer`() {
        assertThat(notManufacturerCheck(CURRENT_MANUFACTURER, osCheck)).isTrue()
    }

    fun `test that banned model check if we are a banned`() {
        assertThat(bannedModelCheck("", osCheck)).isFalse()
    }

    fun `test that banned model check if we are not banned`() {
        assertThat(bannedModelCheck(CURRENT_MODEL, osCheck)).isTrue()
    }

    fun `test that banned board check doesnt fail if not banned`() {
        assertThat(bannedBoardCheck("", osCheck)).isFalse()
    }

    fun `test that banned board check does fail if banned`() {
        assertThat(bannedBoardCheck(CURRENT_BOARD, osCheck)).isTrue()
    }

    fun `test that banned bootloader check doesnt fail if not banned`() {
        assertThat(bannedBootloaderCheck("", osCheck)).isFalse()
    }

    fun `test that banned bootloader check does fail if banned`() {
        assertThat(bannedBootloaderCheck(CURRENT_BOOTLOADER, osCheck)).isTrue()
    }

    fun `test that banned cpus check doesnt fail if not banned`() {
        assertThat(bannedCpusCheck("", osCheck)).isFalse()
    }

    fun `test that banned cpus check does fail if banned`() {
        assertThat(bannedCpusCheck(CURRENT_ABIS.first(), osCheck)).isTrue()
    }

    fun `test that banned device check doesnt fail if not banned`() {
        assertThat(bannedDeviceCheck("", osCheck)).isFalse()
    }

    fun `test that banned device check does fail if banned`() {
        assertThat(bannedDeviceCheck(CURRENT_DEVICE, osCheck)).isTrue()
    }

    fun `test that banned hardware check doesnt fail if not banned`() {
        assertThat(bannedHardwareCheck("", osCheck)).isFalse()
    }

    fun `test that banned hardware check does fail if banned`() {
        assertThat(bannedHardwareCheck(CURRENT_HARDWARE, osCheck)).isTrue()
    }

    fun `test that banned host check doesnt fail if not banned`() {
        assertThat(bannedHostCheck("", osCheck)).isFalse()
    }

    fun `test that banned host check does fail if banned`() {
        assertThat(bannedHostCheck(CURRENT_HOST, osCheck)).isTrue()
    }

    companion object {
        const val CURRENT_SDK = 25
        const val CURRENT_MANUFACTURER = "manufacturer"
        const val CURRENT_MODEL = "model"
        const val CURRENT_BOARD = "board"
        const val CURRENT_BOOTLOADER = "bootloader"
        val CURRENT_ABIS = arrayOf("abi1", "abi2")
        const val CURRENT_HOST = "host"
        const val CURRENT_HARDWARE = "hardware"
        const val CURRENT_DEVICE = "device"
    }
}
