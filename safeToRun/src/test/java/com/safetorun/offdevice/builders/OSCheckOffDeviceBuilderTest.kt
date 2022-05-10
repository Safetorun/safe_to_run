package com.safetorun.offdevice.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.models.builders.deviceInformationBuilder
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class OSCheckOffDeviceBuilderTest : TestCase() {

    private val osQuery = mockk<OSInformationQuery>()

    override fun setUp() {
        every { osQuery.osVersion() } returns OS_VERSION
        every { osQuery.manufacturer() } returns MANUFACTURER
        every { osQuery.model() } returns MODEL
        every { osQuery.board() } returns BOARD
        every { osQuery.bootloader() } returns BOOTLOADER
        every { osQuery.cpuAbi() } returns listOf(CPU_ABI)
        every { osQuery.host() } returns HOST
        every { osQuery.hardware() } returns HARDWARE
        every { osQuery.device() } returns DEVICE
    }

    fun `test that os check off device adds the device check`() {
        val osCheckOffDeviceBuilder = OSCheckOffDeviceBuilder(osQuery)
        val result = with(deviceInformationBuilder("")) {
            osCheckOffDeviceBuilder.buildOffDeviceResultBuilder(this)
            buildPartial()
        }

        assertThat(result.osCheck.osVersion).isEqualTo(OS_VERSION.toString())
        assertThat(result.osCheck.manufacturer).isEqualTo(MANUFACTURER)
        assertThat(result.osCheck.model).isEqualTo(MODEL)
    }

    companion object {
        const val OS_VERSION = 123
        const val MANUFACTURER = "manufacturer"
        const val MODEL = "model"
        const val BOARD = "board"
        const val BOOTLOADER = "bootloader"
        const val CPU_ABI = "cpu_abi"
        const val HOST = "host"
        const val HARDWARE = "hardware"
        const val DEVICE = "device"
    }
}
