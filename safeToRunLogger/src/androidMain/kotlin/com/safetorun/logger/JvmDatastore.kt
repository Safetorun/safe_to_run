package com.safetorun.logger

import com.safetorun.logger.models.SafeToRunEvents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

internal class JvmDatastore(
    private val storageDirectory: File,
    private val verifyFile: File.() -> Boolean
) :
    DataStore {

    override suspend fun store(data: SafeToRunEvents) {
        newFile(data.uuid)
            .writeText(Json.encodeToString(SafeToRunEvents.serializer(), data))
    }

    override suspend fun retrieve(): Flow<SafeToRunEvents> =
        listFiles()
            ?.asFlow()
            ?.filter { it.isDirectory.not() }
            ?.map { it.readText() }
            ?.map { Json.decodeFromString(it) } ?: emptyFlow()

    override suspend fun delete(uuid: String) {
        fileForUuid(uuid)
            .run {
                if (verifyFile() && exists()) {
                    delete()
                }
            }

    }

    private fun fileForUuid(uuid: String) = File(storageDirectory, uuid)
    private fun newFile(uuid: String) = File(storageDirectory, uuid)
    private fun listFiles() = storageDirectory.listFiles()
}
