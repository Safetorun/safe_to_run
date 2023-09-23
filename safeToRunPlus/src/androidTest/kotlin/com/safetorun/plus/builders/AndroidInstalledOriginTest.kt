package com.safetorun.plus.builders

import android.content.Context
import android.content.pm.InstallSourceInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PackageInfoFlags
import android.os.Build
import com.google.common.truth.Truth.assertThat
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.plus.SharedInstallOrigin.INSTALLER_PACKAGE
import com.safetorun.plus.mockBuildField
import com.safetorun.plus.queries.getInstaller
import com.safetorun.plus.setOlderAndroidVersion
import com.safetorun.plus.setTiramusu
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase

internal class AndroidInstalledOriginTest : TestCase() {

    private val context by lazy { mockk<Context>() }
    private val pm by lazy { mockk<PackageManager>() }
    private val osInformation by lazy { mockk<OSInformationQuery>() }

    @Suppress("DEPRECATION") // Need to do it for older devices
    override fun setUp() {

        mockkStatic(PackageInfoFlags::class)

        every { context.packageName } returns packageName
        every { context.packageManager } returns pm

        every { pm.getInstallSourceInfo(packageName) } returns mockk<InstallSourceInfo>().apply {
            every { installingPackageName } returns INSTALLER_PACKAGE
        }

        every { pm.getInstallerPackageName(packageName) } returns INSTALLER_PACKAGE
        every { pm.getInstallerPackageName(fakeName) } returns null

        every { pm.getInstallSourceInfo(fakeName) } returns mockk<InstallSourceInfo>().apply {
            every { installingPackageName } returns null
        }

    }

    fun `test that android install origin returns based on the context when null`() {
        // Given
        every { context.packageName } returns fakeName
        setTiramusu(osInformation)

        // When
        val result = context.getInstaller(osInformation)

        // Then
        assertThat(result).isEqualTo("Not found")
    }

    fun `test that android install origin returns based on the context pre TIRAMUSU`() {
        // Given
        setOlderAndroidVersion(osInformation)

        // When
        val result = context.getInstaller(osInformation)

        // Then
        assertThat(result).isEqualTo(INSTALLER_PACKAGE)
    }


    fun `test that android install origin returns based on the context`() {
        // Given
        setTiramusu(osInformation)

        // When
        val result = context.getInstaller(osInformation)

        // Then
        assertThat(result).isEqualTo(INSTALLER_PACKAGE)
    }

    companion object {
        private const val packageName = "com.me"
        private const val fakeName = "com.fake"
    }
}
