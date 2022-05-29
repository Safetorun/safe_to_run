package com.safetorun.builder

import com.safetorun.inputverification.builders.configurationParser
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

/**
 * Plugin extension for configuration of safe to run plugin
 */
open class SafeToRunBuilderPluginExtension {
    /**
     * Path to place generated code
     */
    var generatedCodePath: String = "src/main/kotlin/"

    /**
     * Path to configuration
     */
    var configurationPath: String = ""
}

/**
 * Plugin code for generating safe to run functions and config
 */
class SafeToRunBuilderPlugin : Plugin<Project> {
    /**
     * Apply plugin
     *
     * @param plugin @see super
     */
    override fun apply(project: Project) {
        val extension: SafeToRunBuilderPluginExtension = project.extensions
            .create("safeToRun", SafeToRunBuilderPluginExtension::class.java)

        project.task("safeToRun")
            .doLast {
                generate(
                    File(project.projectDir, extension.generatedCodePath),
                    configurationParser(File(project.projectDir, extension.configurationPath).toURL())
                )
            }
    }
}
