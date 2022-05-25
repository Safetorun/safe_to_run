plugins {
    id("org.jetbrains.kotlin.jvm")
    id("io.gitlab.arturbosch.detekt")
    id("com.diffplug.spotless")
    id("com.github.johnrengelman.shadow") version "5.0.0"
    `java-gradle-plugin`
}

apply(plugin = "com.vanniktech.android.junit.jacoco")

//ext {
//    PUBLISH_GROUP_ID = 'com.safetorun'
//    PUBLISH_VERSION = project['safeToRunVersion']
//    PUBLISH_ARTIFACT_ID = 'safeToRunBuilder'
//}

val serializationVersion: String by project

val directory = "${rootProject.projectDir}/scripts/dependencies/"
apply(from = "$directory/test_dependencies.gradle")

dependencies {
    implementation("com.squareup:kotlinpoet:1.11.0")
    implementation(project(":safeToRunInternal"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
}

gradlePlugin {
    plugins {
        create("safeToRun") {
            id = "com.safetorun.builder"
            implementationClass = "com.safetorun.builder.SafeToRunBuilderPlugin"
        }
    }
}