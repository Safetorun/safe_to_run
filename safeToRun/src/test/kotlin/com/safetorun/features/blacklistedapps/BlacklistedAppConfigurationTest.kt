package com.safetorun.features.blacklistedapps

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

@Suppress("DEPRECATION")
internal class BlacklistedAppConfigurationTest : TestCase() {

    private val mockContext = mockk<Context>(relaxed = true)
    private val mockPackageManager = mockk<PackageManager>()
    private val mockPackage1 = mockk<PackageInfo>()
    private val packageInfos = listOf(mockPackage1)

    override fun setUp() {
        every { mockContext.packageManager } returns mockPackageManager
        every { mockPackageManager.getInstalledPackages(any<PackageManager.PackageInfoFlags>()) } returns packageInfos
        every { mockPackageManager.getInstalledPackages(any<Int>()) } returns packageInfos
        mockPackage1.packageName = IS_PRESENT_PACKAGE
    }

    fun `test that we get a failure rule if package is installed`() {
        assertTrue(mockContext.blacklistedAppCheck(IS_PRESENT_PACKAGE))
    }

    fun `test that we get a pass rule if package is installed`() {
        assertFalse(mockContext.blacklistedAppCheck(NOT_PRESENT_PACKAGE))
    }

    fun `test that we get a pass rule if empty package list provided`() {
        assertFalse(mockContext.blacklistedAppCheck())
    }

    companion object {
        private const val IS_PRESENT_PACKAGE = "com.abc.com"
        private const val NOT_PRESENT_PACKAGE = "com.abc.def"
    }
}
