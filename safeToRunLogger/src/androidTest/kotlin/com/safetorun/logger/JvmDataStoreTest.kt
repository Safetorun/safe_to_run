package com.safetorun.logger

import com.safetorun.logger.models.AppMetadata
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

    private val store = JvmDatastore(testDirectory) {
        true
    }

    private val failedChecks = listOf(
        failedCheck(),
        failedCheck()
    )


    @Before
    fun clearDirectory() {
        clear()
    }

    @After
    fun removeDirectory() {
        remove()
    }


    @Test
    fun `test that jvm data store can save and then retrieve a data for failure`() = runTest {
        val failedCheck = failedCheck()
        store.store(failedCheck)
        val retrievedList = store.retrieve().toList()
        assertEquals(1, retrievedList.size)
        assertEquals(failedCheck, retrievedList[0])
    }

    @Test
    fun `test that jvm data store can save and then retrieve data for success`() = runTest {
        val failedCheck = successCheck()
        store.store(failedCheck)
        val retrievedList = store.retrieve().toList()
        assertEquals(1, retrievedList.size)
        assertEquals(failedCheck, retrievedList[0])
    }

    @Test
    fun `test that JVM data store is resilience to deleting a non-existing file`() = runTest {
        store.delete("test")
    }

    @Test
    fun `test that JVM data store is resilience to not reading a directory`() = runTest {
        failedChecks.forEach { store.store(it) }
        File(testDirectory, "inner-dir").mkdirs()

        val retrievedList = store.retrieve().toList()
        assertEquals(2, retrievedList.size)

        File(testDirectory, "inner-dir").deleteRecursively()
    }

    @Test
    fun `test that JVM data store is resilience to not reading a duff file`() = runTest {
        failedChecks.forEach { store.store(it) }
        File(testDirectory, "test-file").writeText("test")

        val retrievedList = store.retrieve().toList()
        assertEquals(2, retrievedList.size)

        File(testDirectory, "test-file").writeText("test")
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
    fun `test that jvm data store can clear`() = runTest {
        failedChecks.forEach { store.store(it) }

        val retrievedList = store.retrieve().toList()
        assertEquals(2, retrievedList.size)

        store.clear()
        assertEquals(store.retrieve().toList().size, 0)
    }

    @Test
    fun `test that jvm can delete`() = runTest {
        failedChecks.forEach { store.store(it) }

        val retrievedList = store.retrieve().toList()
        assertEquals(2, retrievedList.size)
        assertEquals(failedChecks[0], retrievedList[0])
        assertEquals(failedChecks[1], retrievedList[1])

        retrievedList.forEach { store.delete(it.uuid) }
        assertEquals(0, store.retrieve().toList().size)
    }
}

internal fun failedCheck() = SafeToRunEvents.FailedCheck(
    DeviceInformation.empty(),
    AppMetadata.empty(),
    "default"
)

internal fun successCheck() = SafeToRunEvents.SucceedCheck(
    DeviceInformation.empty(),
    AppMetadata.empty(),
    "default"
)
