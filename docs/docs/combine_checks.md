---
id: combinechecks
title: Combine checks
slug: /combinechecks
---

Safe to run (Default) provides the ability to combine multiple checks together, and also
have one check by passes to the check. For example, this rule:

```kotlin
safeToRunCombinedCheck(
    listOf(
        { bannedHardwareCheck("hardware") },
        { bannedBoardCheck("board") }
    )
)
```

Produces a check that will fail if either the hardware is 'hardware' 
or the board is 'board'

### Unless 

An unless check is a second list of parameters, which if this doesn't fail (e.g. it returns
false) will negate the failure in the first list. For example

```kotlin
safeToRunCombinedCheck(
    listOf { installOriginCheckWithDefaultsCheck() },
    listOf { !BuildConfig.DEBUG }
)
```

If the install origins aren't the defaults (e.g. amazon or google play store) then the 
check will fail UNLESS we're running a debug build - in which case the check will pass 
