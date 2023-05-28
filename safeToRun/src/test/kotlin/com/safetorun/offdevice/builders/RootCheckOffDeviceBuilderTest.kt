package com.safetorun.offdevice.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.models.builders.deviceInformationBuilder
import org.junit.jupiter.api.Test


internal class RootCheckOffDeviceBuilderTest {

    @Test
    fun `test that the root check off device builder defers to the underlying root check`() {
        assertThat(
            RootCheckOffDeviceBuilder(rootCheck = { true })
                .buildOffDeviceResultBuilder(deviceInformationBuilder(""))
                .build()
                .isRooted
        ).isTrue()
    }
}
