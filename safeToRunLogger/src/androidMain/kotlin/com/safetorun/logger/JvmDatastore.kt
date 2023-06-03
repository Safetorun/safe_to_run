package com.safetorun.logger

import com.safetorun.logger.models.SafeToRunEvents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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

    @Suppress("TooGenericExceptionCaught", "SwallowedException") // we want to ignore any errors
    override suspend fun retrieve(): Flow<SafeToRunEvents> =
        listFiles()
            .asFlow()
            .filter { it.isDirectory.not() }
            .map { it.readText() }
            .map<String, SafeToRunEvents?> {
                try {
                    Json.decodeFromString(it)
                } catch (ex: Exception) {
                    null
                }
            }
            .filterNotNull()

    override suspend fun delete(uuid: String) {
        fileForUuid(uuid)
            .run {
                if (exists()) {
                    if (deleteRecursively().not()) {
                        delete()
                    }
                }
            }

    }

    override suspend fun clear() {
        listFiles()
            .asFlow()
            .onEach { it.deleteRecursively() }
            .collect()
    }

    private fun fileForUuid(uuid: String) = File(storageDirectory, uuid)
    private fun newFile(uuid: String) = File(storageDirectory, uuid)
    private fun listFiles() = storageDirectory.listFiles()?.toList() ?: emptyList()
}
