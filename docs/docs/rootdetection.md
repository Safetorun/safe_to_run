---
id: rootdetection title: Root detection slug: /rootdetection
---

:::info

Root detection is only available on device

:::

Root detection is mostly deferred to RootBeer. The configuration looks like this:

```kotlin
rootDetection {
    tolerateRoot = false
    tolerateBusyBox = true
}
```

We also have an additional option (see blacklisting apps for more information) to deny running if we are able to see
blacklisted apps

For example:

```kotlin
 blacklistConfiguration {
    +"com.abc.def"
    blacklistRootingApps()
}.error()
```
