package com.safetorun.logger

import java.io.File

val testDirectory by lazy {
    File("test_data_dir_temp")
        .also {
            if (it.exists().not()) {
                it.mkdir()
            }
        }
}

fun clear() {
    testDirectory
        .listFiles()
        ?.forEach { it.delete() }
    testDirectory.mkdir()
}

fun remove() {
    clear()
    testDirectory.delete()
}
