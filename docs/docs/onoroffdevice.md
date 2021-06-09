---
id: onoroffdevice
title: On or off device?
slug: /onoroffdevice
---

:::danger

Off device is currently in beta

:::

# Two modes

Safe to run offers two modes of operation, it is
possible - recommended even - to use both. 

The 'on device' mode runs on device, where the checks 
happen on the device itself. The 'off-device' mode works
by sending device information to a remote server, and
the check is done there. 

# Pros and cons

The on device modes' main advantage is that the check can happen
almost immediately, and it does not involve a backend system in
order to run. The disadvantage, is that it is easier for an attacker
to see what checks are being performed by your service, and they can
more easily bypass them. A further advantage of the on-device approach
is that your backend can make a decision about whether to run a
command - e.g., your backend might refuse to change a password if it's 
operating on a rooted phone, or, if you are writing a banking application 
you might refuse to send a payment if the phone is considered unsafe

# Usage

Beyond the setup of the backend (discussed in another section) the difference
is in the configuration. An example configuration for 'On device' looks like
this

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
            +packageName
        }.error()
        
        // Etc
    }
)

SafeToRun.isSafeToRun()
```

Whereas the configuration for off device is done elsewhere, for
the application configuration is done like this:

```kotlin
 val str = safeToRunOffDevice(
    "https://rygl69bpz0.execute-api.eu-west-1.amazonaws.com/Prod/",
    "5bzdwZ8Drs1AIsmJAx0M37bndOeEkwbv6pI5fjx1"
)
    .isSafeToRun {
    Log.v("IsSafeToRun", it.signedResult)
}

// (Note the callback, if you handle your own threading you can call:)
str.isSafeToRun()
```
