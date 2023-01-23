package com.safetorun.logger

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.File
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
internal class AndroidDataStoreTest {

    private val context by lazy { mockk<Context>() }

    private val testDirectory by lazy {
        File("test_data_dir_{0}".format(System.currentTimeMillis()))
            .also {
                if (it.exists().not()) {
                    it.mkdir()
                }
            }
    }

    private val store by lazy { AndroidDataStore(context) }

    fun clearDirectory() {
        every { context.filesDir } returns testDirectory

        testDirectory
            .listFiles()
            ?.forEach { it.delete() }
    }

    fun removeDirectory() {
        clearDirectory()
        testDirectory.delete()
    }

    fun `test that android data store rejects deletion of a directory traversal`() = runTest {
        clearDirectory()
        val failedCheck = failedCheck()
        store.store(failedCheck)
        val retrievedList = store.retrieve().toList()
        assertEquals(1, retrievedList.size)
        assertEquals(failedCheck, retrievedList[0])
        removeDirectory()
    }
}
