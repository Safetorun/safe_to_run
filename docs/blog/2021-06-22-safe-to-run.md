---
slug: emulator-detection
title: "Secure android apps with Safe to run"
author: Daniel Llewellyn
author_url: https://github.com/dllewellyn
tags: [security, android, safetorun, kotlin]
---

:::danger 

No library or app can guarantee not running on a rooted phone because of the nature of rooted phones, and any tamper detection could be removed or changed in reality — this app should work with most attackers, and make it hard enough to make it not worth it for many others.
Background

::: 

Safe to run is intended to provide a layer of security for Android applications from rooted phones, reverse engineering, binary modification, malicious apps and some security vulnerabilities.

In principle, you set the parameters for a safe device, (one where the debugger is not attached, one with a minimum OS version, one not rooted etc) and ask ‘is it safe to run’.

You know best the time and place to ask the question-maybe you do it on app launch and throw an exception, maybe you ask when some tries to make a payment to someone else and reject the payment or maybe you do it before retrieving some data from the backend.

### Checks
We have the following checks that are configurable in safe to run

* Signature checks — check the signature of the binary (set multiple for multiple certs)
* Debug check — check if the debugger is attached or if the app is debuggable
* Device check — blacklist a combination of OS version and device manufacturer
* Root check — check for signs which point to the device being rooted
* Other packages check — check for the presence of other packages, e.g. You might not want to run if you know a specific app is running because it’s known malware that might attack your app
* Install origin check — check for the installing package of your app, this can help to make reverse engineering harder

### Sample usage

Every time you want to run a check execute:

```SafeToRun.safeToRun()```
