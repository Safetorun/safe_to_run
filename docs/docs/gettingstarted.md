---
id: gettingstarted 
title: Getting started 
slug: /gettingstarted
---

Gradle:

```groovy
implementation "io.github.dllewellyn.safetorun:safetorun:1.0.1"
implementation "io.github.dllewellyn.safetorun:safeToRunCore:1.0.1"
```

An example of a configuration, done in App class:

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

Then use like this

```kotlin
SafeToRun.safeToRun()
```

## Errors or Warns

The syntax above allows you to configure an 'error' or a 'warn' this simply impacts to type of response that is returned
from safe to run. In practice, a response that would return an error message if configure to 'errorIf' will return

```kotlin 
data class SafeToRunReportFailure(val failureReason: String, val failureMessage: String) : SafeToRunReport()
```

If set to warn it will return

```kotlin
data class SafeToRunWarning(val warnReason: String, val warningMessage: String) : SafeToRunReport()
```

The difference is simply to help your app to distinguish between the two
