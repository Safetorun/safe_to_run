package com.safetorun.builder

import com.safetorun.inputverification.builders.configurationParser
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

open class SafeToRunBuilderPluginExtension {
    var generatedCodePath: String = "src/main/kotlin/"
    var configurationPath: String = ""
}

class SafeToRunBuilderPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension: SafeToRunBuilderPluginExtension = project.extensions
            .create("safeToRun", SafeToRunBuilderPluginExtension::class.java)

        project.task("safeToRun")
            .doLast {

                SafeToRunCodeGenerator(
                    File(project.projectDir, extension.generatedCodePath),
                    configurationParser(File(project.projectDir, extension.configurationPath).toURL())
                ).generate()
            }
    }
}
