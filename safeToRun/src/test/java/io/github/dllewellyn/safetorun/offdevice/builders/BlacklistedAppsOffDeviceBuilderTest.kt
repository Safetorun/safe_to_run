package io.github.dllewellyn.safetorun.offdevice.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.features.blacklistedapps.InstalledPackagesQuery
import com.safetorun.models.builders.deviceInformationBuilder
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
            blacklistedAppOffDeviceBuilder.buildOffDeviceResultBuilder(deviceInformationBuilder(""))

        // Then
        assertThat(result.buildPartial().blacklistedApp.installedPackages).isEqualTo(installedPackages)
    }

    companion object {
        val installedPackages = listOf("com.abc", "def.hij", "klm.nmo")
    }
}
