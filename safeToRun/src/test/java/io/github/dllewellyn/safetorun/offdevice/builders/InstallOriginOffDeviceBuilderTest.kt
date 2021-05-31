package io.github.dllewellyn.safetorun.offdevice.builders

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.features.installorigin.InstallOriginQuery
import io.github.dllewellyn.safetorun.models.models.deviceInformationBuilder
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
        val installOriginQueryOffDeviceBuilder = InstallOriginOffDeviceBuilder(installOriginQuery)

        // When
        val result = installOriginQueryOffDeviceBuilder
            .buildOffDeviceResultBuilder(deviceInformationBuilder(""))

        // Then
        assertThat(result.buildPartial().installOrigin.installOriginPackageName).isEqualTo(PACKAGE_NAME)
    }

    fun `test that install origin off device builder populates correctly if null`() {
        // Given
        every { installOriginQuery.getInstallPackageName() } returns null
        val installOriginQueryOffDeviceBuilder = InstallOriginOffDeviceBuilder(installOriginQuery)

        // When
        val result = installOriginQueryOffDeviceBuilder
            .buildOffDeviceResultBuilder(deviceInformationBuilder(""))

        // Then
        assertThat(result.buildPartial().installOrigin.installOriginPackageName).isNull()
    }

    companion object {
        const val PACKAGE_NAME = "Package name"
    }
}
