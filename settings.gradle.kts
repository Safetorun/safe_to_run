pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}


include(
    ":safeToRun",
    ":app",
    ":safeToRunCore",
    ":safeToRunInternal",
    ":safeToRunInputValidation",
    ":safeToRunBuilder",
    ":safetorunpinscreen",
    ":safeToRunLogger"
)

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            version("kotlin", "1.7.20")

            library(
                "jetbrains-kotlin-std",
                "org.jetbrains.kotlin",
                "kotlin-stdlib"
            ).versionRef("kotlin")

            library(
                "jetbrains-kotlin-reflect",
                "org.jetbrains.kotlin",
                "kotlin-reflect"
            ).versionRef("kotlin")
        }
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}
rootProject.name = "Safe to run"

