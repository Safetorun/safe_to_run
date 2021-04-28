---
id: oscheck
title: OS Check
slug: /oscheck
---


Use to blacklist Manufacturer / OS Version combinations


```kotlin
// OS Blacklist version
osDetectionCheck(
    conditionalBuilder {
        with(minOsVersion(22))
        and(notManufacturer("Abc"))
    }
).warn()
```