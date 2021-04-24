---
id: gettingstarted 
title: Getting started 
slug: /gettingstarted
---

An example of a configuration, done in App class:

```kotlin
SafeToRun.init(
    configure {

        // Root beer (detect root)
        this errorIf rootDetection {
            tolerateRoot = false
            tolerateBusyBox = true
        }


        // Black list certain apps
        this errorIf blacklistConfiguration {
            +"com.abc.def"
            +"com.google.earth"
        }

        this errorIf verifySignatureConfig("cSP1O3JN/8+Ag14WAOeOEnwAnpY=")

        // OS Blacklist version
        this warnIf osDetectionCheck(
            conditionalBuilder {
                with(minOsVersion(22))
                and(notManufacturer("Abc"))
            }
        )

        this warnIf debugCheck()

    }
)
```

Then use like this

```kotlin
SafeToRun.safeToRun(this)
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