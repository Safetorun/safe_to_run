plugins {
    kotlin("multiplatform")
    id("org.jetbrains.dokka")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization").version("1.5.0")
    id("org.jetbrains.kotlinx.kover")

}


ext {
    this["PUBLISH_GROUP_ID"] = "com.safetorun"
    this["PUBLISH_VERSION"] = libs.versions.safeToRun.get()
    this["PUBLISH_ARTIFACT_ID"] = "safeToRunPlus"
}

apply(from = "${rootProject.projectDir}/scripts/publish-module.gradle")

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(project(":safeToRunLogger"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.mockk)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project(":safeToRunInputValidation"))
                implementation(project(":safeToRunInternal"))
                implementation(libs.androidx.workmanager)
                implementation(libs.rootbeer)
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(libs.mockk.server)
                implementation(libs.jupiter)
                implementation(libs.robolectric)
                implementation(libs.google.truth)
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
    namespace = "com.safetorun.plus"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

