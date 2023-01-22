package com.safetorun.builder

import com.safetorun.builder.input.generate
import com.safetorun.inputverification.configurationParser
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.io.FileNotFoundException

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
     * @param project @see super
     */
    override fun apply(project: Project) {
        val extension: SafeToRunBuilderPluginExtension = project.extensions
            .create("safeToRun", SafeToRunBuilderPluginExtension::class.java)



        project.task("safeToRun")
            .doLast {
                val file = File(
                    project.projectDir,
                    extension.configurationPath
                )

                val configuration = try {
                    configurationParser(file)
                } catch (exception: FileNotFoundException) {
                    throw SafeToRunBuilderException(
                        "Failed to find valid configuration in file ${file.absolutePath}",
                        exception
                    )
                }

                generate(
                    File(project.projectDir, extension.generatedCodePath),
                    configuration
                )
            }
    }
}
