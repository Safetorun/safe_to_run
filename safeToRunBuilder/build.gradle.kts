plugins {
    id("org.jetbrains.kotlin.jvm")
    id("io.gitlab.arturbosch.detekt")
    id("com.diffplug.spotless")
    `java-gradle-plugin`
}

apply(plugin= "com.vanniktech.android.junit.jacoco")

//ext {
//    PUBLISH_GROUP_ID = 'com.safetorun'
//    PUBLISH_VERSION = project['safeToRunVersion']
//    PUBLISH_ARTIFACT_ID = 'safeToRunBuilder'
//}

dependencies {
    implementation("com.squareup:kotlinpoet:1.11.0")
}

gradlePlugin {
    plugins {
        create("safeToRun") {
            id = "com.safetorun.builder"
            implementationClass = "com.safetorun.builder.SafeToRunBuilderPlugin"
        }
    }
}