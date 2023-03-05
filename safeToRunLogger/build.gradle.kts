plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("org.jetbrains.dokka")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization").version("1.5.0")
    id("org.jetbrains.kotlinx.kover")

}

val safeToRunVersion = libs.versions.safeToRunVersion.toString()

ext {
    this["PUBLISH_GROUP_ID"] = "com.safetorun"
    this["PUBLISH_VERSION"] = "${safeToRunVersion}-alpha"
    this["PUBLISH_ARTIFACT_ID"] = "safeToRunLogger"
}

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

kotlin {
    android()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "safeToRunLogger"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project(":safeToRunInputValidation"))
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(libs.robolectric)
                implementation(libs.junit)
                implementation(libs.mockk)
                implementation(libs.kotlinx.coroutines.android)
            }
        }
    }
}

android {
    packagingOptions {
        resources.excludes.apply {
            add("META-INF/*")
        }
    }
    namespace = "com.safetorun.logger"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

