package com.safetorun.offdevice.builders

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PackageInfoFlags
import com.google.common.truth.Truth.assertThat
import com.safetorun.features.blacklistedapps.AndroidInstalledPackagesQuery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase

internal class AndroidInstalledPackagesQueryTest : TestCase() {

    private val context by lazy { mockk<Context>() }
    private val pm by lazy { mockk<PackageManager>() }

    override fun setUp() {

        mockkStatic(PackageInfoFlags::class)

        every { PackageInfoFlags.of(any()) } returns mockk(relaxed = true)
        every { context.packageManager } returns pm
        every { pm.getInstalledPackages(0) } returns installedPackages
        every { pm.getInstalledPackages(any<PackageInfoFlags>()) } returns installedPackages

    }

    fun `test that android installed packages returns based on the context`() {
        // Given
        val androidInstalledPackagesQuery = AndroidInstalledPackagesQuery(context)

        // When
        val result = androidInstalledPackagesQuery.listInstalledPackages()

        // Then
        assertThat(result).isEqualTo(installedPackagesNames)
    }

    companion object {
        private val installedPackagesNames = listOf("com.abc", "def.hij", "klm.nmo")
        private val installedPackages = installedPackagesNames
            .map { packageName ->
                PackageInfo().apply {
                    this.packageName = packageName
                }
            }
    }
}
