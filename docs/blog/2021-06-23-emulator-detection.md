---
slug: emulator-detection
title: "Emulator detection"
author: Daniel Llewellyn
author_url: https://github.com/dllewellyn
tags: [security, android, safetorun, emulator detection, kotlin]
---

### What?

Emulator detection is the ability to tell when your application is running on an emulator rather than a real device, but why would you want to do this?


### Why?

Reverse engineers, pentesters and hackers tend to like running your app on an emulator can be make it far easier reveal what your application is doing. A somewhat convoluted example is looking at an application’s files in their private directory. For example:

In that case, we can see that by preventing our app running on an emulated device, it can make it more difficult for a penetration tester to observe our application.

### How?
We’re going to use the library Safe to run for both the detection and to help identify the emulator.

Core Safe to run (android library) The purpose of this configuration is to provide a simple and extensible framework

Safe to run has a utility to show device information:

`Log.v("Device information", deviceInformation().toString())`

If we run this into application on the Android emulator, it looks like this:

`DeviceInformation(osCheck=OsCheck(osVersion=30, manufacturer=Google, model=sdk_gphone_x86, board=goldfish_x86, bootloader=unknown, cpuAbi=[x86, armeabi-v7a, armeabi], host=abfarm-us-west1-c-0089, hardware=ranchu, device=generic_x86_arm), installOrigin=InstallOrigin(installOriginPackageName=), signatureVerification=SignatureInformation(signature=))`

And just to double check with another emulator we get this result

`DeviceInformation(osCheck=OsCheck(osVersion=30, manufacturer=Google, model=sdk_gphone_x86_arm, board=goldfish_x86, bootloader=unknown, cpuAbi=[x86, armeabi-v7a, armeabi], host=abfarm-us-west1-c-0007, hardware=ranchu, device=generic_x86_arm), installOrigin=InstallOrigin(installOriginPackageName=), signatureVerification=SignatureInformation(signature=))`

There’s da few things we could pick out here, but in particular we can probably start to build up this as an emulator check. We’ll write up a configuration for safe to run:

```kotlin
SafeToRun.init(
    configure {
        osDetectionCheck(
            conditionalBuilder {
                with(bannedBootloader("unknown"))
                and(bannedDevice("generic_x86_arm"))
                and(bannedBoard("goldfish_x86"))
            }
        ).error()
    }
)
Log.v("SafeToRunResult", SafeToRun.isSafeToRun().toString())
```
This check will result in the following failure

`MultipleReports(reports=[MultipleReports(reports=[SafeToRunReportFailure(failureReason=os-config-failure, failureMessage=Banned bootloader unknown == unknown)])])`
Genymotion
Another popular emulator is GenyMotion. If we go to the cloud GenyMotion, we can test our experiments on devices there. After connecting and installing out devices, we can have a look again at the deviceInformation() output
Genymotion Cloud SaaS
Edit description
cloud.geny.io

`DeviceInformation(osCheck=OsCheck(osVersion=28, manufacturer=Genymotion, model=Huawei P30 Pro, board=unknown, bootloader=unknown, cpuAbi=[x86], host=68d6ec695a7c, hardware=vbox86, device=vbox86p), installOrigin=InstallOrigin(installOriginPackageName=), signatureVerification=SignatureInformation(signature=))

Easy peasy, we can create a new rule for Genymotion:

```kotlin
osDetectionCheck(conditionalBuilder {
    with(bannedBoard("unknown"))
    and(notManufacturer("Genymotion"))
    and(bannedBootloader("unknown"))
}).error()
```

Bluestacks
Another popular emulator is bluestacks. After installing and running it, we get this:

`DeviceInformation(osCheck=OsCheck(osVersion=25, manufacturer=samsung, model=SM-G955F, board=universal8895, bootloader=unknown, cpuAbi=[x86_64, x86, arm64-v8a, armeabi-v7a, armeabi], host=Build2, hardware=samsungexynos8895, device=dream2lte), installOrigin=InstallOrigin(installOriginPackageName=), signatureVerification=SignatureInformation(signature=))`

This is actually a tricky one for now because the rest of the description is very similar to a real device that we wouldn’t want to block. We can safely use this rule for bluestacks though:

`with(bannedBootloader(OsCheckConstants.UNKNOWN))`

Making it easier
From 1.0.2 onwards, Safe to run provides two utility functions for these two emulators (more coming soon)

```kotlin
SafeToRun.init {
    osDetectionCheck(banAvdEmulator()).error()
    osDetectionCheck(banGenymotionEmulator()).error()
    osDetectionCheck(banBluestacksEmulator()).error()
}
```

### Conclusion

Hopefully that gives us an idea of how we can perform emulator detection on Android, Safe to run provides a number of ways of checking for device information so you can tweak parameters for emulator detection — naturally there are a plenty of emulators we’e not discussed here that you might want to write a check for.
Enjoy
