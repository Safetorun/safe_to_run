package com.safetorun.logger.metadata

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PackageInfoFlags
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

@Suppress("DEPRECATION")
internal class MetadataBuilderTest {

    private val context by lazy {
        mockk<Context>().apply {
            every { packageName } returns PACKAGE_NAME
            every { packageManager } returns mockk<PackageManager>().apply {
                every { getInstalledPackages(any<PackageInfoFlags>()) } returns listOf(
                    packageInfo
                )
                every {
                    getPackageInfo(
                        PACKAGE_NAME,
                        any<PackageInfoFlags>()
                    )
                } returns packageInfo
            }
        }
    }
    private val packageInfo by lazy {
        mockk<android.content.pm.PackageInfo>().apply {
            every { versionName } returns APP_VERSION
            every { versionCode } returns LONG_VERSION.toInt()
            every { longVersionCode } returns LONG_VERSION
            every { firstInstallTime } returns FIRST_INSTALL_TIME
            every { lastUpdateTime } returns LAST_UPDATE_TIME
        }
    }

    @Test
    fun `test that metadata can be build using expected output`() {
        context.metadata()
    }

    companion object {
        private const val PACKAGE_NAME = "com.test.package"
        private const val APP_VERSION = "1.0.1"
        private const val LONG_VERSION = 321L
        private const val FIRST_INSTALL_TIME = 123L
        private const val LAST_UPDATE_TIME = 456L
    }
}
