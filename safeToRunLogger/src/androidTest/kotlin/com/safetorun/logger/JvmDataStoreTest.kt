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
            .also { if (it.exists().not()) {
                it.mkdir()}
            }
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
        val store = JvmDatastore(testDirectory) {
            true
        }

        val failedCheck = SafeToRunEvents.FailedCheck(DeviceInformation.empty(), "default")
        store.store(failedCheck)
        val retrievedList = store.retrieve().toList()
        assertEquals(1, retrievedList.size)
        assertEquals(failedCheck, retrievedList[0])
    }
}
