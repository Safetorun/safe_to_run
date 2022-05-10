package com.safetorun.offdevice.builders

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.google.common.truth.Truth.assertThat
import com.safetorun.features.blacklistedapps.AndroidInstalledPackagesQuery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class AndroidInstalledPackagesQueryTest : TestCase() {

    private val context = mockk<Context>()

    override fun setUp() {
        every { context.packageManager } returns mockk<PackageManager>().apply {
            every { getInstalledPackages(0) } returns installedPackages
        }
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
