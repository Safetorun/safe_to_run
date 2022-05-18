package com.safetorun.builder

import org.gradle.api.Plugin
import org.gradle.api.Project

data class GreetingPluginExtension(
    val greeter: String = "Baeldung",
    val message: String = "Message from the plugin!"
)

class SafeToRunBuilderPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension: GreetingPluginExtension = project.extensions
            .create("greeting", GreetingPluginExtension::class.java)

        project.task("hello")
            .doLast { task ->
                println(
                    "Hello, " + extension.greeter
                )
                println(
                    "I have a message for You: " + extension.message
                )
            }
    }
}
