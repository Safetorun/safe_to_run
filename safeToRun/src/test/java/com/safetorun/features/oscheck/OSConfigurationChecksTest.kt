package com.safetorun.features.oscheck

import android.os.Build
import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.utils.mockBuildField
import io.mockk.mockkStatic
import junit.framework.TestCase

internal class OSConfigurationChecksTest : TestCase() {
    override fun setUp() {
        mockkStatic(Build::class)

        mockBuildField(CURRENT_DEVICE, "DEVICE", Build::class.java)
        mockBuildField(CURRENT_HARDWARE, "HARDWARE", Build::class.java)
        mockBuildField(CURRENT_HOST, "HOST", Build::class.java)
        mockBuildField(CURRENT_ABIS, "SUPPORTED_ABIS", Build::class.java)
        mockBuildField(CURRENT_BOOTLOADER, "BOOTLOADER", Build::class.java)
        mockBuildField(CURRENT_BOARD, "BOARD", Build::class.java)
        mockBuildField(CURRENT_MODEL, "MODEL", Build::class.java)
        mockBuildField(CURRENT_MANUFACTURER, "MANUFACTURER", Build::class.java)
        mockBuildField(CURRENT_SDK, "SDK_INT", Build.VERSION::class.java)
    }

    fun `test that min os version check passes if os version is greater than min`() {
        assertThat(minOsVersionCheck(CURRENT_SDK - 1)).isFalse()
    }

    fun `test that min os version check passes if os version is equal than min`() {
        assertThat(minOsVersionCheck(CURRENT_SDK)).isFalse()
    }

    fun `test that min os version check fails if os version is less than min`() {
        assertThat(minOsVersionCheck(CURRENT_SDK + 1)).isTrue()
    }

    fun `test that manufacturer check passes if we are not a banned manufacturer`() {
        assertThat(notManufacturerCheck("Not banned")).isFalse()
    }

    fun `test that manufacturer check fails if we are a banned manufacturer`() {
        assertThat(notManufacturerCheck(CURRENT_MANUFACTURER)).isTrue()
    }

    fun `test that banned model check if we are a banned`() {
        assertThat(bannedModelCheck("")).isFalse()
    }

    fun `test that banned model check if we are not banned`() {
        assertThat(bannedModelCheck(CURRENT_MODEL)).isTrue()
    }

    fun `test that banned board check doesnt fail if not banned`() {
        assertThat(bannedBoardCheck("")).isFalse()
    }

    fun `test that banned board check does fail if banned`() {
        assertThat(bannedBoardCheck(CURRENT_BOARD)).isTrue()
    }

    fun `test that banned bootloader check doesnt fail if not banned`() {
        assertThat(bannedBootloaderCheck("")).isFalse()
    }

    fun `test that banned bootloader check does fail if banned`() {
        assertThat(bannedBootloaderCheck(CURRENT_BOOTLOADER)).isTrue()
    }

    fun `test that banned cpus check doesnt fail if not banned`() {
        assertThat(bannedCpusCheck("")).isFalse()
    }

    fun `test that banned cpus check does fail if banned`() {
        assertThat(bannedCpusCheck(CURRENT_ABIS.first())).isTrue()
    }

    fun `test that banned device check doesnt fail if not banned`() {
        assertThat(bannedDeviceCheck("")).isFalse()
    }

    fun `test that banned device check does fail if banned`() {
        assertThat(bannedDeviceCheck(CURRENT_DEVICE)).isTrue()
    }

    fun `test that banned hardware check doesnt fail if not banned`() {
        assertThat(bannedHardwareCheck("")).isFalse()
    }

    fun `test that banned hardware check does fail if banned`() {
        assertThat(bannedHardwareCheck(CURRENT_HARDWARE)).isTrue()
    }

    fun `test that banned host check doesnt fail if not banned`() {
        assertThat(bannedHostCheck("")).isFalse()
    }

    fun `test that banned host check does fail if banned`() {
        assertThat(bannedHostCheck(CURRENT_HOST)).isTrue()
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
