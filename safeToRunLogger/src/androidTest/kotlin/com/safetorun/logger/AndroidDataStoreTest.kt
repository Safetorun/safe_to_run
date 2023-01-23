package com.safetorun.logger

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.File
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
internal class AndroidDataStoreTest {

    private val context by lazy { mockk<Context>() }

    private val testDirectory by lazy {
        File("test_data_dir_123")
            .also {
                if (it.exists().not()) {
                    it.mkdir()
                }
            }
    }

    private val store by lazy { AndroidDataStore(context) }

    private fun clearDirectory() {
        testDirectory
            .listFiles()
            ?.forEach { it.delete() }
    }

    @Test
    fun `test that android data store rejects deletion of a directory traversal`() = runTest {
        every { context.filesDir } returns testDirectory
        clearDirectory()

        val failedCheck = failedCheck()
        store.store(failedCheck)
        val retrievedList = store.retrieve().toList()
        assertEquals(1, retrievedList.size)
        assertEquals(failedCheck, retrievedList[0])

        clearDirectory()
    }
}
