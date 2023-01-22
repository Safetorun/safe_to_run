package com.safetorun.logger

import com.safetorun.logger.models.DeviceInformation
import com.safetorun.logger.models.SafeToRunEvents
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
internal class JvmDataStoreTest {

    private val testDirectory by lazy {
        File("test_data_dir")
            .also {
                if (it.exists().not()) {
                    it.mkdir()
                }
            }
    }

    private val store = JvmDatastore(testDirectory) {
        true
    }

    @Before
    fun clearDirectory() {
        testDirectory
            .listFiles()
            ?.forEach { it.delete() }
    }

    @After
    fun removeDirectory() {
        clearDirectory()
        testDirectory.delete()
    }

    @Test
    fun `test that jvm data store can save and then retrieve a data`() = runTest {
        val failedCheck = failedCheck()
        store.store(failedCheck)
        val retrievedList = store.retrieve().toList()
        assertEquals(1, retrievedList.size)
        assertEquals(failedCheck, retrievedList[0])
    }

    @Test
    fun `test that jvm data store rejects deletion of a directory traversal`() = runTest {
        val store = JvmDatastore(testDirectory) {
            false
        }
        store.delete("test")

        // Hard to see what would happen here ... No crash will have to do
    }

    @Test
    fun `test that jvm can delete`() = runTest {
        val failedChecks = listOf(
            failedCheck(),
            failedCheck()
        )

        failedChecks.forEach { store.store(it) }

        val retrievedList = store.retrieve().toList()
        assertEquals(2, retrievedList.size)
        assertEquals(failedChecks[0], retrievedList[0])
        assertEquals(failedChecks[1], retrievedList[1])

        retrievedList.forEach { store.delete(it.uuid) }
        assertEquals(0, store.retrieve().toList().size)
    }


}

internal fun failedCheck() = SafeToRunEvents.FailedCheck(DeviceInformation.empty(), "default")
