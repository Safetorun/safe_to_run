pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}


include(
    ":safeToRun",
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
//            from(files("../gradle/libs.versions.toml"))
        }
    }

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}
rootProject.name = "Safe to run"

