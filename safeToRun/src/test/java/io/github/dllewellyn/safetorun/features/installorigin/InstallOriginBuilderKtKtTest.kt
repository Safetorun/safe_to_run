package io.github.dllewellyn.safetorun.features.installorigin

import android.content.Context
import android.content.pm.InstallSourceInfo
import android.content.pm.PackageManager
import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.features.installorigin.SharedInstallOrigin.PACKAGE_NAME_RETURNS
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class InstallOriginBuilderKtKtTest : TestCase() {
    private val context = mockk<Context>()
    private val packageManager = mockk<PackageManager>()

    override fun setUp() {
        every { context.packageManager } returns packageManager
        every { packageManager.getInstallerPackageName(any()) } returns PACKAGE_NAME_RETURNS
        every { context.packageName } returns "com.anything"
        every { packageManager.getInstallSourceInfo(any()) } returns mockk<InstallSourceInfo>().apply {
            every { installingPackageName } returns PACKAGE_NAME_RETURNS
        }

        every { context.resources } returns SharedInstallOrigin.setupAMockResources()
    }

    fun `test that if the package returns null we return the null message`() {
        // Given
        every { packageManager.getInstallerPackageName(any()) } returns null
        every { packageManager.getInstallSourceInfo(any()) } returns mockk<InstallSourceInfo>().apply {
            every { installingPackageName } returns null
        }

        assertThat(context.installOriginCheckWithDefaultsCheck(PACKAGE_NAME_RETURNS)).isTrue()
    }

    fun `test that if the package returns a different package to allowed it will return an error`() {
        assertThat(context.installOriginCheckWithDefaultsCheck("abc")).isTrue()
    }

    fun `test that if the package returns package to allowed it will return ok`() {
        assertThat(context.installOriginCheckWithDefaultsCheck(PACKAGE_NAME_RETURNS)).isFalse()
    }
}
