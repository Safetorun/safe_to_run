package com.safetorun.inputverification.builders

import com.safetorun.inputverification.builders.dto.SafeToRunInputVerificationDto
import com.safetorun.inputverification.builders.dto.toCore
import com.safetorun.inputverification.builders.model.SafeToRunInputVerification
import kotlinx.serialization.json.Json
import java.net.URL

/**
 * Function to configure files
 *
 * @param fileName configuration file url
 */
fun configurationParser(fileName: URL) =
    Json.decodeFromString(
        SafeToRunInputVerificationDto.serializer(),
        fileName.readText(),
    ).let {
        SafeToRunInputVerification(
            fileConfiguration = it.fileConfiguration.map { conf -> conf.toCore() },
            urlConfigurations = it.urlConfigurations.map { conf -> conf.toCore() }
        )
    }


class InputVerificationBuilder internal constructor() {

    fun build() = SafeToRunInputVerificationDto()
}
