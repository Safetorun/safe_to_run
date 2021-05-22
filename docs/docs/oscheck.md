---
id: oscheck
title: OS Check
slug: /oscheck
---


Use to blacklist Manufacturer / OS Version combinations

## On device 

```kotlin
// OS Blacklist version
osDetectionCheck(
    conditionalBuilder {
        with(minOsVersion(22))
        and(notManufacturer("Abc"))
    }
).warn()
```

## Off device

Slightly more involved for off device

```kotlin

osDetectionCheck(
    context,
    with(osInformation()) {
        conditionalBuilder {
            with(minOsVersion(22))
            and(notManufacturer("Abc"))
        }
    }
).error()
```
