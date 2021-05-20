package io.github.dllewellyn.safetorun.offdevice.builders

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.features.blacklistedapps.InstalledPackagesQuery
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDtoBuilder
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class BlacklistedAppsOffDeviceBuilderTest : TestCase() {

    private val blacklistedAppCheck = mockk<InstalledPackagesQuery>()

    override fun setUp() {
        every { blacklistedAppCheck.listInstalledPackages() } returns installedPackages
    }

    fun `test that blacklisted app off device test will provide all packages returned`() {
        // Given
        val blacklistedAppOffDeviceBuilder = BlacklistedAppsOffDeviceBuilder(blacklistedAppCheck)

        // When
        val result =
            blacklistedAppOffDeviceBuilder.buildOffDeviceResultBuilder(DeviceInformationDtoBuilder(""))

        // Then
        assertThat(result.buildPartial().blacklistedApp.installedPackages).isEqualTo(installedPackages)
    }

    companion object {
        val installedPackages = listOf("com.abc", "def.hij", "klm.nmo")
    }
}
