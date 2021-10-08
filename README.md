# Safe to Run

[![Known Vulnerabilities](https://snyk.io/test/github/safetorun/safe_to_run/badge.svg)](https://snyk.io/test/github/safetorun/safe_to_run)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/64152443e1fa4a30b17a2739294d3d47)](https://www.codacy.com/gh/Safetorun/safe_to_run/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Safetorun/safe_to_run&amp;utm_campaign=Badge_Grade)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dllewellyn_safe_to_run&metric=alert_status)](https://sonarcloud.io/dashboard?id=dllewellyn_safe_to_run) 
[![codecov](https://codecov.io/gh/Safetorun/safe_to_run/branch/master/graph/badge.svg?token=2CUARL5E6B)](https://codecov.io/gh/Safetorun/safe_to_run)

Safe to run (android library)
![Maven central - SafeToRun](https://maven-badges.herokuapp.com/maven-central/io.github.dllewellyn.safetorun/safetorun/badge.svg)

Core
![Maven central - Core](https://maven-badges.herokuapp.com/maven-central/io.github.dllewellyn.safetorun/safeToRunCore/badge.svg)

Input validation
![Maven central - Input validation](https://maven-badges.herokuapp.com/maven-central/io.github.dllewellyn.safetorun/inputverification/badge.svg)



The purpose of this library is to provide a simple and extensible framework you can use in order to check your app
is safe to run, and provide you with a way to verify data from intents or deep links is safe.  

## Documentation

[Documentation website](http://dllewellyn.github.io/safe_to_run/)

## Quickstart

```groovy
implementation "io.github.dllewellyn.safetorun:safetorun:$safeToRunVersion"
implementation "io.github.dllewellyn.safetorun:safeToRunCore:$safeToRunVersion"
implementation "io.github.dllewellyn.safetorun:inputverification:$safeToRunVersion"
```

## Safe to run input verification

A fuller discussion can be found here:

[Verify URL](https://safetorun.github.io/safe_to_run/docs/verifyurls)

### Urls 

Here's a sample which will only allow safetorun.com as the host, and only
allowed the parameterName with the name "param" of type string.

```kotlin
"https://safetorun.com?param=abc".urlVerification {
    "safetorun.com".allowHost()
    allowParameter {
        allowedType = AllowedType.String
        parameterName = "param"
    }
} == true 
```

We are able to provide more permissive options, for example:

```kotlin§
"https://safetorun.com?param=abc".urlVerification {
    "safetorun.com".allowHost()
    allowAnyParameter()
} == true
```

### Files

### Allow specific private file

We can use safe to run for files too: 

Allowing a specific file 

```kotlin
val isFileSafeToOpen = uri.verifyFile(this) {
    // This
    File(context.filesDir + "files/", "safe_to_read.txt").allowExactFile()

    // Is the same as this:
    addAllowedExactFile(File(context.filesDir + "files/", "safe_to_read.txt"))
}
```

or maybe adding a directory 

```kotlin
val isFileSafeToOpen = uri.verifyFile(this) {
    // This
    addAllowedParentDirectory(context.filesDir.allowDirectory())

    // Is the same as this:
    FileUriMatcherBuilder.FileUriMatcherCheck(
        context.filesDir,
        false
    )
}
```

See docs for full information, and "app" for an example


### Recompilation protection 

Safe to run uses inline functions as an added level of protection against reverse engineering. It is
recommended that you use the inline implementation *in many places* throughout the application in 
order to harden against reverse engineering.

```kotlin
 private inline fun canIRun(actionOnFailure: () -> Unit) {
      if (safeToRun(buildSafeToRunCheckList {
              add {
                  banAvdEmulatorCheck()
              }

              add {
                  blacklistedAppCheck()
              }

              add {
                  rootDetectionCheck()
              }

              add {
                  banGenymotionEmulatorCheck()
              }

              add {
                  banBluestacksEmulatorCheck()
              }

              add {
                  safeToRunCombinedCheck(
                      listOf(
                          { bannedHardwareCheck("hardware") },
                          { bannedBoardCheck("board") }
                      )
                  )
              }

              add {
                  safeToRunCombinedCheck(
                      listOf { installOriginCheckWithDefaultsCheck() },
                      listOf { !BuildConfig.DEBUG }
                  )

              }
        
              add {
               verifySignatureCheck("Abc")
              }
          })()) {
          actionOnFailure()
      }
  }
```
