---
id: gettingstarted
title: Getting started
slug: /
---

An example of a configuration, done in App:


```kotlin
  SafeToRun.init(
            configure {

                // Root beer (detect root)
                plus {
                    rootDetection {
                        tolerateRoot = false
                        tolerateBusyBox = true
                    }
                }


                // Black list certain apps
                plus {
                    blacklistConfiguration {
                        +"com.abc.def"
                        +"com.google.earth"
                    }
                }

                // OS Blacklist version
                plus {
                    osDetectionCheck(
                        conditionalBuilder {
                            with(minOsVersion(22))
                            and(notManufacturer("Abc"))
                        }
                    )
                }
            }
        )
```


Then use like this


```
SafeToRun.safeToRun(this)
```