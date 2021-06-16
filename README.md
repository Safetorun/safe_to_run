# Safe to Run

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3ee63330045749fba264c69f0c796993)](https://app.codacy.com/gh/dllewellyn/safe_to_run?utm_source=github.com&utm_medium=referral&utm_content=dllewellyn/safe_to_run&utm_campaign=Badge_Grade_Settings)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dllewellyn_safe_to_run&metric=alert_status)](https://sonarcloud.io/dashboard?id=dllewellyn_safe_to_run) [![codecov](https://codecov.io/gh/dllewellyn/safe_to_run/branch/master/graph/badge.svg?token=WUGLW5ALWB)](https://codecov.io/gh/dllewellyn/safe_to_run)

Core
 ![Maven central - Core](https://maven-badges.herokuapp.com/maven-central/io.github.dllewellyn.safetorun/safetorun/badge.svg)

Safe to run (android library)
![Maven central - Core](https://maven-badges.herokuapp.com/maven-central/io.github.dllewellyn.safetorun/safeToRunCore/badge.svg)



The purpose of this configuration is to provide a simple and extensible framework you can use in order to check the
phone you're running on is safe to run your application.

## Documentation

[Documentation website](http://dllewellyn.github.io/safe_to_run/)

## Quickstart

```groovy
implementation "io.github.dllewellyn.safetorun:safetorun:1.0.3"
implementation "io.github.dllewellyn.safetorun:safeToRunCore:1.0.3"
```


### Safe to run 

Safe to run uses inline functions as an added level over protection against reverse engineering. It is
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

### Safe to run reporting 

Safe to run reporting gives give a similar configuration step, but a more detailed report
for the errors than the above example

```kotlin
 SafeToRun.init(
            configure {

                // Root beer (detect root)
                rootDetection {
                    tolerateBusyBox = true
                }.error()

                // Black list certain apps
                blacklistConfiguration {
                    +"com.abc.def"
                    +packageName
                }.error()

                verifySignatureConfig("cSP1O3JN/8+Ag14WAOeOEnwAnpY=")
                    .error()

                // OS Blacklist version
                osDetectionCheck(
                    conditionalBuilder {
                        with(minOsVersion(MIN_OS_VERSION))
                        and(notManufacturer("Abc"))
                        and(bannedModel("bannedModel"))
                    }
                ).warn()

                osDetectionCheck(
                    conditionalBuilder {
                        with(bannedModel("Pixel 4a (5G)"))
                    }
                ).warn()

                installOriginCheckWithDefaults().warn()

                osDetectionCheck(banAvdEmulator()).error()
                debugCheck().warn()
            }
        )
```

Usage:

```kotlin
SafeToRun.safeToRun(this)
```


See docs for full information, and "app" for an example
