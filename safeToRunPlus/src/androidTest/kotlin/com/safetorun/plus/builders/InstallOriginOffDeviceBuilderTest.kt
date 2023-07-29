package com.safetorun.plus.offdevice.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.features.installorigin.InstallOriginQuery
import com.safetorun.models.builders.deviceInformationBuilder
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class InstallOriginOffDeviceBuilderTest : TestCase() {

    private val installOriginQuery = mockk<InstallOriginQuery>()

    override fun setUp() {
        every { installOriginQuery.getInstallPackageName() } returns PACKAGE_NAME
    }

    fun `test that install origin off device builder populates correctly`() {
        // Given
        val installOriginQueryOffDeviceBuilder =
            com.safetorun.plus.offdevice.builders.InstallOriginOffDeviceBuilder(installOriginQuery)

        // When
        val result = installOriginQueryOffDeviceBuilder
            .buildOffDeviceResultBuilder(deviceInformationBuilder(""))

        // Then
        assertThat(result.build().installOrigin.installOriginPackageName).isEqualTo(PACKAGE_NAME)
    }

    fun `test that install origin off device builder populates correctly if empty`() {
        // Given
        every { installOriginQuery.getInstallPackageName() } returns ""
        val installOriginQueryOffDeviceBuilder =
            com.safetorun.plus.offdevice.builders.InstallOriginOffDeviceBuilder(installOriginQuery)

        // When
        val result = installOriginQueryOffDeviceBuilder
            .buildOffDeviceResultBuilder(deviceInformationBuilder(""))

        // Then
        assertThat(result.build().installOrigin.installOriginPackageName).isEmpty()
    }

    companion object {
        const val PACKAGE_NAME = "Package name"
    }
}
