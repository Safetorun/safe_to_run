package com.safetorun.inputverification.builders

import com.safetorun.inputverification.dto.FileConfigurationDto
import com.safetorun.inputverification.dto.ParentConfigurationDto
import java.io.File


/**
 * Builder for a configuration
 */
class ParentConfigurationBuilder internal constructor(private val directory: String) {
    /**
     * Whether to allow files to be written into subdirectories
     */
    var allowSubdirectories = false


    internal fun build() = ParentConfigurationDto(
        allowSubdirectories = allowSubdirectories,
        directory = directory
    )
}

/**
 * Class for file configuration building
 */
class FileConfigurationBuilder internal constructor() {

    /**
     * Whether to allow any file to pass the check
     */
    var allowAnyFile = false

    private var allowedFiles = mutableListOf<String>()
    private var allowedDirectory = mutableListOf<ParentConfigurationDto>()

    /**
     * Allow a file
     */
    fun String.allowFile() {
        allowedFiles.add(this)
    }

    /**
     * Allow a file
     */
    fun File.allowFile() {
        allowedFiles.add(this.absolutePath)
    }

    /**
     * Allow directory
     */
    fun String.allowDirectory(builder: ParentConfigurationBuilder.() -> Unit) {
        allowedDirectory.add(
            ParentConfigurationBuilder(this)
                .apply(builder)
                .build()
        )
    }

    /**
     * Allow directory
     */
    fun File.allowDirectory(builder: ParentConfigurationBuilder.() -> Unit) {
        allowedDirectory.add(
            ParentConfigurationBuilder(this.absolutePath)
                .apply(builder)
                .build()
        )
    }

    /**
     * Add a file to the allowed list
     */
    operator fun File.unaryPlus() {
        allowFile()
    }

    /**
     * Add a file to the allowed list
     */
    operator fun String.unaryPlus() {
        allowFile()
    }

    internal fun build() = FileConfigurationDto(
        allowAnyFile = allowAnyFile,
        allowedExactFiles = allowedFiles,
        allowedParentDirectories = allowedDirectory
    )
}
