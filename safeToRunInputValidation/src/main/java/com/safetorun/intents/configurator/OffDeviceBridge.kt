package com.safetorun.intents.configurator

import com.safetorun.inputverification.model.AllowedTypeCore
import com.safetorun.inputverification.model.FileConfigurations
import com.safetorun.inputverification.model.SafeToRunInputVerification
import com.safetorun.inputverification.model.UrlConfigurations
import com.safetorun.intents.url.params.AllowedType
import com.safetorun.intents.file.FileUriMatcherBuilder
import java.io.File

/**
 * Register all safe to run inputs from a configuration file
 *
 * @receiver the verification to register
 */
fun SafeToRunInputVerification.register() {
    urlConfigurations.forEach { it.register() }
    fileConfiguration.forEach { it.register() }
}

internal fun FileConfigurations.register() =
    registerFileVerification(name) {
        allowAnyFile = configuration.allowAnyFile
        configuration.allowedParentDirectories.forEach {
            File(it.directory).allowDirectory(it.allowSubdirectories)
        }

        configuration.allowedExactFiles.forEach {
            File(it).allowExactFile()
        }
    }

internal fun UrlConfigurations.register() =
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

private fun AllowedTypeCore.toAllowedType() =
    when (this) {
        AllowedTypeCore.Any -> AllowedType.Any
        AllowedTypeCore.String -> AllowedType.String
        AllowedTypeCore.Boolean -> AllowedType.Boolean
        AllowedTypeCore.Int -> AllowedType.Int
        AllowedTypeCore.Double -> AllowedType.Double
    }
