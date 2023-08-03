package com.safetorun.plus.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.features.installorigin.InstallOriginQuery
import com.safetorun.plus.SharedInstallOrigin.INSTALLER_PACKAGE
import com.safetorun.plus.offdevice.builders.InstallOriginOffDeviceBuilder
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class InstallOriginOffDeviceBuilderTest : TestCase() {

    private val installOriginQuery = mockk<InstallOriginQuery>()

    override fun setUp() {
        every { installOriginQuery.getInstallPackageName() } returns INSTALLER_PACKAGE
    }

    fun `test that install origin off device builder populates correctly`() {
        // Given
        val installOriginQueryOffDeviceBuilder =
            InstallOriginOffDeviceBuilder(installOriginQuery)

        // When
        val result = installOriginQueryOffDeviceBuilder
            .buildOffDeviceResultBuilder(deviceInformationBuilder(""))

        // Then
        assertThat(result.build().installOrigin.installOriginPackageName).isEqualTo(INSTALLER_PACKAGE)
    }

    fun `test that install origin off device builder populates correctly if empty`() {
        // Given
        every { installOriginQuery.getInstallPackageName() } returns ""
        val installOriginQueryOffDeviceBuilder =
            InstallOriginOffDeviceBuilder(installOriginQuery)

        // When
        val result = installOriginQueryOffDeviceBuilder
            .buildOffDeviceResultBuilder(deviceInformationBuilder(""))

        // Then
        assertThat(result.build().installOrigin.installOriginPackageName).isEmpty()
    }
}
