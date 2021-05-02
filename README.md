# Safe to Run

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dllewellyn_safe_to_run&metric=alert_status)](https://sonarcloud.io/dashboard?id=dllewellyn_safe_to_run)


The purpose of this configuration is to provide a simple and extensible framework you can use in order to check the
phone you're running on is safe to run your application.

## Documentation

[Documentation website](http://dllewellyn.github.io/safe_to_run/)

## Quickstart

```groovy
implementation "io.github.dllewellyn.safetorun:safetorun:1.0.0"
implementation "io.github.dllewellyn.safetorun:safeToRunCore:1.0.0"
```

In Activity, fragment or app 

```kotlin
SafeToRun.init(
    configure {

        // Root beer (detect root)
        rootDetection {
            tolerateRoot = false
            tolerateBusyBox = true
        }.error()

        // Black list certain apps
        blacklistConfiguration {
            +"com.abc.def"
            +"com.google.earth"
        }.error()

        verifySignatureConfig("cSP1O3JN/8+Ag14WAOeOEnwAnpY=")
            .error()

        // OS Blacklist version
        osDetectionCheck(
            conditionalBuilder {
                with(minOsVersion(22))
                and(notManufacturer("Abc"))
            }
        ).warn()

        debugCheck().warn()

        installOriginCheckWithDefaults().error()
    }
)
```

Usage:

```kotlin
SafeToRun.safeToRun(this)
```


See docs for full information, and "app" for an example