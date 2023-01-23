package com.safetorun.logger

import android.content.Context
import com.safetorun.intents.file.verifyFile
import java.io.File

internal class AndroidDataStore(private val context: Context) : DataStore by JvmDatastore(
    storageDirectory = File(context.filesDir, SAFETORUN_DIR)
        .also {
            if (it.exists().not()) {
                it.mkdir()
            }
        },
    verifyFile = {
        verifyFile(context) {
            context.filesDir.allowDirectory()
        }
    }
) {
    companion object {
        const val SAFETORUN_DIR = "safetorun"
    }
}
