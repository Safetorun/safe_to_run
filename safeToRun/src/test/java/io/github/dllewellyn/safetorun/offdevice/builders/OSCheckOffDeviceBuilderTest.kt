package io.github.dllewellyn.safetorun.offdevice.builders

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDtoBuilder
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class OSCheckOffDeviceBuilderTest : TestCase() {

    private val osQuery = mockk<OSInformationQuery>()

    override fun setUp() {
        every { osQuery.osVersion() } returns OS_VERSION
        every { osQuery.manufacturer() } returns MANUFACTURER
        every { osQuery.model() } returns MODEL
    }

    fun `test that os check off device adds the device check`() {
        val osCheckOffDeviceBuilder = OSCheckOffDeviceBuilder(osQuery)
        val result = with(DeviceInformationDtoBuilder("")) {
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
    }
}
