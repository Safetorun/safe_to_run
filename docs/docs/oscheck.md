---
id: oscheck
title: OS Check
slug: /oscheck
---


Use to blacklist Manufacturer / OS Version combinations


```
val conditional = conditionalBuilder {
    with(osInformationQuery) {
        with(minOsVersion(30))
        and(notManufacturer(DODGY_MANUFACTURER))
    }
}

val osDetectionRule = OSDetectionConfig(listOf(conditional))
```