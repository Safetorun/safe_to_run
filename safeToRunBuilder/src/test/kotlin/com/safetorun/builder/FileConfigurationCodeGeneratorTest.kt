package com.safetorun.builder

import com.google.common.truth.Truth.assertThat
import com.safetorun.inputverification.model.FileConfiguration
import com.safetorun.inputverification.model.FileConfigurations
import com.safetorun.inputverification.model.ParentConfiguration
import org.junit.Test

internal class FileConfigurationCodeGeneratorTest {

    @Test
    fun `test that generating file code generator gives correct output`() {
        val allowSubdirectories = false
        val parentDirectory = "directory"
        val fileCode = FileConfigurations(
            "configuration",
            FileConfiguration(
                allowAnyFile = true,
                allowedExactFiles = listOf("fileA", "fileB"),
                allowedParentDirectories = listOf(
                    ParentConfiguration(parentDirectory, allowSubdirectories)
                )
            )
        )

        val result = generateFileConfigurationCode(fileCode)
        assertThat(result.toString()).isEqualTo("""
com.safetorun.intents.configurator.registerFileVerification("${fileCode.name}")
 {
  allowAnyFile = ${fileCode.configuration.allowAnyFile}
  java.io.File("${fileCode.configuration.allowedExactFiles.first()}").allowExactFile()
  java.io.File("${fileCode.configuration.allowedExactFiles.last()}").allowExactFile()
  java.io.File("$parentDirectory").allowDirectory(${allowSubdirectories})
}

""".trimIndent())

    }
}
