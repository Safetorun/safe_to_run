package com.safetorun.logger.metadata

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PackageInfoFlags
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

@Suppress("DEPRECATION")
internal class MetadataBuilderTest {
    private val packageInfo = mockk<PackageInfo>()

    private val context by lazy {
        mockk<Context> {
            every { packageName } returns PACKAGE_NAME
            every { packageManager } returns mockk {
                retrievePackageManager(this)
            }
        }
    }

    @Before
    fun setup() {
        packageInfo.versionCode = LONG_VERSION.toInt()
        every { packageInfo.longVersionCode } returns LONG_VERSION
        packageInfo.firstInstallTime = FIRST_INSTALL_TIME
        packageInfo.lastUpdateTime = LAST_UPDATE_TIME
        packageInfo.versionName = APP_VERSION
    }

    @Test
    fun `test that metadata can be build using expected output`() {
        context.metadata()
    }

    private fun retrievePackageManager(
        packageManager: PackageManager
    ) {
        every { packageManager.getInstalledPackages(any<PackageInfoFlags>()) } returns listOf(
            packageInfo
        )
        every {
            packageManager.getPackageInfo(
                PACKAGE_NAME,
                any<PackageInfoFlags>()
            )
        } returns packageInfo

        every {
            packageManager.getPackageInfo(
                PACKAGE_NAME,
                0
            )
        } returns packageInfo
    }

    companion object {
        private const val PACKAGE_NAME = "com.test.package"
        private const val APP_VERSION = "1.0.1"
        private const val LONG_VERSION = 321L
        private const val FIRST_INSTALL_TIME = 123L
        private const val LAST_UPDATE_TIME = 456L
    }
}
