plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization").version("1.5.0")
    id("org.jetbrains.kotlinx.kover")

}

val coroutinesVersion: String by project
val serializationVersion: String by project
val robolectricVersion: String by project
val mockkVersion: String by project

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
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(project(":safeToRunInputValidation"))
                implementation("org.robolectric:robolectric:$robolectricVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("org.jetbrains .kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
            }
        }
    }
}

android {
    namespace = "com.safetorun.logger"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

