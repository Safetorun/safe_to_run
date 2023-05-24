package com.safetorun.logger

import java.io.File

/**
 * Test dir for storing data
 */
val testDirectory by lazy {
    File("test_data_dir_temp")
        .also {
            if (it.exists().not()) {
                it.mkdir()
            }
        }
}

internal fun clear() {
    testDirectory
        .listFiles()
        ?.forEach { it.delete() }
    testDirectory.mkdir()
}

internal fun remove() {
    clear()
    testDirectory.delete()
}
