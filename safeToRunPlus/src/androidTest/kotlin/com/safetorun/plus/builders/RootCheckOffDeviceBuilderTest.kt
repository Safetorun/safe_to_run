package com.safetorun.plus.offdevice.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.models.builders.deviceInformationBuilder
import junit.framework.TestCase


internal class RootCheckOffDeviceBuilderTest : TestCase()  {

    fun `test that the root check off device builder defers to the underlying root check`() {
        assertThat(
            com.safetorun.plus.offdevice.builders.RootCheckOffDeviceBuilder(rootCheck = { true })
                .buildOffDeviceResultBuilder(deviceInformationBuilder(""))
                .build()
                .isRooted
        ).isTrue()
    }

    fun `test that the root check off device builder defers to the underlying root check but false `() {
        assertThat(
            com.safetorun.plus.offdevice.builders.RootCheckOffDeviceBuilder(rootCheck = { false })
                .buildOffDeviceResultBuilder(deviceInformationBuilder(""))
                .build()
                .isRooted
        ).isFalse()
    }
}
