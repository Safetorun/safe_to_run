package com.safetorun.logger

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.safetorun.logger.metadata.MetadataBuilderTest
import com.safetorun.logger.models.SafeToRunEvents
import com.safetorun.logger.models.VerifyType
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.joinAll
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
    fun `test that backend synch can store data to disk when called for a success verify`() =
        runTest {
            context.loggerForVerify<String>(checkName, VerifyType.File, this)
                .invoke(true, extraData)
            this.testScheduler.runCurrent()

            val logs = context.logs().toList()

            assertEquals(1, logs.size)
            assertEquals(SafeToRunEvents.SuccessVerify::class, logs[0]::class)
            assertEquals("data", (logs[0] as SafeToRunEvents.SuccessVerify).extra)
            context.deleteLog(logs[0].uuid)
            context.clearLogs()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test that backend synch can store data to disk when called for a fail verify`() =
        runTest {
            context.loggerForVerify<String>(checkName, VerifyType.File, this)
                .invoke(true, extraData)
            this.testScheduler.runCurrent()

            val logs = context.logs().toList()

            assertEquals(1, logs.size)
            assertEquals(SafeToRunEvents.SuccessVerify::class, logs[0]::class)
            assertEquals(extraData, (logs[0] as SafeToRunEvents.SuccessVerify).extra)
            context.deleteLog(logs[0].uuid)
            context.clearLogs()
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test that backend synch can store data to disk when called`() = runTest {
        context.loggerForCheck(checkName, this).invoke(false)
        this.testScheduler.runCurrent()

        val logs = context.logs().toList()

        assertEquals(1, logs.size)
        assertEquals(SafeToRunEvents.FailedCheck::class, logs[0]::class)
        context.deleteLog(logs[0].uuid)
        context.clearLogs()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test that backend synch can store data to disk when called (pass)`() = runTest {
        context.loggerForCheck(checkName, this).invoke(true)
        this.testScheduler.runCurrent()

        joinAll()

        val logs = context.logs().toList()

        assertEquals(1, logs.size)
        assertEquals(SafeToRunEvents.SucceedCheck::class, logs[0]::class)
        context.deleteLog(logs[0].uuid)
        context.clearLogs()
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

    companion object {
        const val checkName = "CheckName"
        const val testMessage = "Test message"
        const val extraData = "data"
    }
}
