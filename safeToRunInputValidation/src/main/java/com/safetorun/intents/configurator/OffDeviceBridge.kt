package com.safetorun.intents.configurator

import com.safetorun.inputverification.builders.AllowedTypeDto
import com.safetorun.inputverification.builders.FileConfigurationsDto
import com.safetorun.inputverification.builders.UrlConfigurationsDto
import com.safetorun.intents.file.FileUriMatcherBuilder
import com.safetorun.intents.url.params.AllowedType
import java.io.File

internal fun FileConfigurationsDto.register() =
    registerFileVerification(name) {
        allowAnyFile = configuration.allowAnyFile
        configuration.allowedParentDirectories.forEach {
            addAllowedParentDirectory(
                FileUriMatcherBuilder.FileUriMatcherCheck(
                    File(it.directory),
                    it.allowSubdirectories
                )
            )
        }

        configuration.allowedExactFiles.forEach {
            addAllowedExactFile(File(it))
        }
    }

internal fun UrlConfigurationsDto.register() =
    registerUrlVerification(name) {
        if (configuration.allowAnyParameter) {
            allowAnyParameter()
        }

        if (configuration.allowAnyUrl) {
            allowAnyUrl()
        }

        configuration.allowedHost.forEach { it.allowHost() }
        configuration.allowedUrls.forEach { it.allowUrl() }
        configuration.allowParameters.forEach {
            allowParameter {
                parameterName = it.parameterName
                allowedType = it.allowedType.toAllowedType()
            }
        }
    }

private fun AllowedTypeDto.toAllowedType() =
    when (this) {
        AllowedTypeDto.Any -> AllowedType.Any
        AllowedTypeDto.String -> AllowedType.String
        AllowedTypeDto.Boolean -> AllowedType.Boolean
        AllowedTypeDto.Int -> AllowedType.Int
        AllowedTypeDto.Double -> AllowedType.Double
    }
