package com.safetorun.plus.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.plus.offdevice.builders.BlacklistedAppsOffDeviceBuilder
import junit.framework.TestCase

internal class BlacklistedAppsOffDeviceBuilderTest : TestCase() {

    fun `test that blacklisted app off device test will provide all packages returned`() {
        // Given
        val blacklistedAppOffDeviceBuilder =
            BlacklistedAppsOffDeviceBuilder { installedPackages }

        // When
        val result =
            blacklistedAppOffDeviceBuilder.buildOffDeviceResultBuilder(deviceInformationBuilder(""))

        // Then
        assertThat(result.build().blacklistedApp.installedPackages).isEqualTo(installedPackages)
    }

    companion object {
        val installedPackages = listOf("com.abc", "def.hij", "klm.nmo")
    }
}
