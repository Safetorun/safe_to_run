plugins {
    id("org.jetbrains.kotlin.jvm")
    id("io.gitlab.arturbosch.detekt")
    id("com.diffplug.spotless")
    id("org.jetbrains.dokka")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    `java-gradle-plugin`
    id("org.jetbrains.kotlinx.kover")

}

val safeToRunVersion: String by project

ext {
    set("PUBLISH_GROUP_ID", "com.safetorun")
    set("PUBLISH_VERSION", "${safeToRunVersion}-alpha")
    set("PUBLISH_ARTIFACT_ID", "safeToRunBuilder")
}

group = "com.safetorun"
version = safeToRunVersion


val directory = "${rootProject.projectDir}/scripts/dependencies/"
apply(from = "$directory/test_dependencies.gradle")

dependencies {
    implementation(libs.kotlinpoet)
    implementation(project(":safeToRunInternal"))
    implementation(libs.kotlinx.serialization.json)
}

gradlePlugin {
    plugins {
        create("safeToRun") {
            id = "com.safetorun.builder"
            implementationClass = "com.safetorun.builder.SafeToRunBuilderPlugin"
        }
    }
}
