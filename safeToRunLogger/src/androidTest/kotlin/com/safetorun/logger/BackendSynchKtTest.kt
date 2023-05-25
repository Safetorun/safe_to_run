package com.safetorun.logger

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.safetorun.logger.metadata.MetadataBuilderTest
import com.safetorun.logger.models.SafeToRunEvents
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

internal class BackendSynchKtTest {

    private val packageInfo = mockk<PackageInfo>().apply {
        every { longVersionCode } returns 1L
        versionName = "Test"
    }

    private val context by lazy {
        mockk<Context> {
            every { packageName } returns MetadataBuilderTest.PACKAGE_NAME
            every { packageManager } returns mockk {
                retrievePackageManager(this)
            }

            every { filesDir } returns testDirectory
        }
    }


    @Before
    fun clearDirectory() {
        clear()
    }

    @After
    fun removeDirectory() {
        remove()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test that backend synch can store data to disk when called`() = runTest {
        context.loggerForCheck("testname", this).invoke(false)

        val logs = context.logs().toList()

        assertEquals(1, logs.size)
        assertEquals(SafeToRunEvents.FailedCheck::class, logs[0]::class)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test that backend synch can store data to disk when called (pass)`() = runTest {
        context.loggerForCheck("testname", this).invoke(true)

        val logs = context.logs().toList()

        assertEquals(1, logs.size)
        assertEquals(SafeToRunEvents.SucceedCheck::class, logs[0]::class)
    }

    private fun retrievePackageManager(
        packageManager: PackageManager
    ) {
        every { packageManager.getInstalledPackages(any<PackageManager.PackageInfoFlags>()) } returns listOf(
            packageInfo
        )
        every {
            packageManager.getPackageInfo(
                MetadataBuilderTest.PACKAGE_NAME,
                any<PackageManager.PackageInfoFlags>()
            )
        } returns packageInfo

        every {
            packageManager.getPackageInfo(
                MetadataBuilderTest.PACKAGE_NAME,
                0
            )
        } returns packageInfo
    }
}
