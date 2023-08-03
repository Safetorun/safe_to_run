package com.safetorun.plus.builders

import android.os.Build
import com.google.common.truth.Truth.assertThat
import com.safetorun.plus.queries.OSInformationQueryAndroid
import com.safetorun.plus.setOlderAndroidVersion
import io.mockk.mockkStatic
import junit.framework.TestCase
import java.lang.reflect.Field
import java.lang.reflect.Modifier

internal class OSInformationQueryAndroidTest : TestCase() {

    override fun setUp() {
        mockkStatic(Build::class)
        mockBuildField(sdkInt, "SDK_INT", Build.VERSION::class.java)
        mockBuildField(manufacturer, "MANUFACTURER", Build::class.java)
        mockBuildField(model, "MODEL", Build::class.java)
        mockBuildField(board, "BOARD", Build::class.java)
        mockBuildField(bootLoader, "BOOTLOADER", Build::class.java)
        mockBuildField(cpuAbi, "SUPPORTED_ABIS", Build::class.java)
        mockBuildField(host, "HOST", Build::class.java)
        mockBuildField(hardware, "HARDWARE", Build::class.java)
        mockBuildField(device, "DEVICE", Build::class.java)
    }

    fun `test that CPU ABIs return empty on old android versions`() {
        // Given
        setOlderAndroidVersion()

        // When // Then
        assertThat(OSInformationQueryAndroid().cpuAbi()).isEmpty()
    }

    fun `test that os information query returns data from build class`() {
        // Given


        // When
        val osInformationQuery = OSInformationQueryAndroid()

        // Then
        assertThat(osInformationQuery.osVersion()).isEqualTo(sdkInt)
        assertThat(osInformationQuery.manufacturer()).isEqualTo(manufacturer)
        assertThat(osInformationQuery.model()).isEqualTo(model)
        assertThat(osInformationQuery.board()).isEqualTo(board)
        assertThat(osInformationQuery.bootloader()).isEqualTo(bootLoader)
        assertThat(osInformationQuery.cpuAbi()).isEqualTo(cpuAbi.toList())
        assertThat(osInformationQuery.host()).isEqualTo(host)
        assertThat(osInformationQuery.hardware()).isEqualTo(hardware)
        assertThat(osInformationQuery.device()).isEqualTo(device)
    }

    private fun <T> mockBuildField(v: T, fieldName: String, clazz: Class<*>) {
        val sdkIntField = clazz.getField(fieldName)
        sdkIntField.isAccessible = true
        Field::class.java.getDeclaredField("modifiers").also {
            it.isAccessible = true
            it.set(sdkIntField, sdkIntField.modifiers and Modifier.FINAL.inv())
        }
        sdkIntField.set(null, v)
    }

    companion object {
        private const val sdkInt = 123
        private const val manufacturer = "manufacturer"
        private const val model = "brand"
        private const val board = "board"
        private const val bootLoader = "bootloader"
        private val cpuAbi = arrayOf("cpuAbi", "cpuAbi2")
        private const val host = "host"
        private const val hardware = "hardware"
        private const val device = "devive"

    }
}
