package com.safetorun.logger

import com.safetorun.logger.models.SafeToRunEvents
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File
import java.io.FileNotFoundException
import kotlin.test.assertEquals

private const val INNER_DIR = "inner-dir"

private const val TEST_FILE = "test-file"

private const val TEST_UUID = "test"

private const val DEFAULT_CHECK_NAME = "default"

@OptIn(ExperimentalCoroutinesApi::class)
internal class JvmDataStoreTest {

    private val store = JvmDatastore(testDirectory) {
        true
    }

    private val failedChecks = listOf(
        SafeToRunEvents.SucceedCheck.empty(DEFAULT_CHECK_NAME),
        SafeToRunEvents.FailedCheck.empty(DEFAULT_CHECK_NAME),
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
        val failedCheck = SafeToRunEvents.FailedCheck.empty(DEFAULT_CHECK_NAME)
        store.store(failedCheck)
        val retrievedList = store.retrieve().toList()
        assertEquals(1, retrievedList.size)
        assertEquals(failedCheck, retrievedList[0])
    }

    @Test
    fun `test that jvm data store can save and then retrieve data for success`() = runTest {
        val failedCheck = SafeToRunEvents.SucceedCheck.empty(DEFAULT_CHECK_NAME)
        store.store(failedCheck)
        val retrievedList = store.retrieve().toList()
        assertEquals(1, retrievedList.size)
        assertEquals(failedCheck, retrievedList[0])
    }

    @Test
    fun `test that JVM data store is resilience to deleting a non-existing file`() = runTest {
        store.delete(TEST_UUID)
    }

    @Test
    fun `test that JVM data store is resilience to not reading a directory`() = runTest {
        failedChecks.forEach { store.store(it) }
        File(testDirectory, INNER_DIR).mkdirs()

        val retrievedList = store.retrieve().toList()
        assertEquals(2, retrievedList.size)

        File(testDirectory, INNER_DIR).deleteRecursively()
    }

    @Test
    fun `test that JVM data store is resilience to not reading a duff file`() = runTest {
        failedChecks.forEach { store.store(it) }
        File(testDirectory, TEST_FILE).writeText(TEST_UUID)

        val retrievedList = store.retrieve().toList()
        assertEquals(2, retrievedList.size)

        File(testDirectory, TEST_FILE).writeText(TEST_UUID)
    }

    @Test
    fun `test that jvm data store rejects deletion of a directory traversal`() = runTest {
        val store = JvmDatastore(testDirectory) {
            false
        }
        store.delete(TEST_UUID)

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
    fun `test that jvm store will ignore files that are not in the right directory`() = runTest {
        val store = JvmDatastore(testDirectory) {
            false
        }

        failedChecks.forEach { store.store(it) }

        val retrievedList = store.retrieve().toList()
        assertEquals(0, retrievedList.size)
    }

    @Test(expected = FileNotFoundException::class)
    fun `test that jvm store will throw an exception for a not existing directory`() = runTest {
        val store = JvmDatastore(File("Does not exist")) {
            true
        }

        failedChecks.forEach {
            store.store(it)
        }
    }

    @Test
    fun `test that jvm store will ignore not existing directory`() = runTest {
        val store = JvmDatastore(File("Does not exist")) {
            true
        }

        val retrievedList = store.retrieve().toList()
        assertEquals(0, retrievedList.size)
    }

    @Test
    fun `test that jvm can delete`() = runTest {
        failedChecks.forEach { store.store(it) }

        val retrievedList = store.retrieve().toList()
        assertEquals(2, retrievedList.size)
        retrievedList.forEach { store.delete(it.uuid) }
        assertEquals(0, store.retrieve().toList().size)
    }
}
