---
id: installorigin 
title: Install origin 
slug: /installorigin
---


The purpose of this library is to ensure that packages are not installed from anywhere other than the configured
appstores.

:::note

When installing from android studio or adb, this check will naturally fail - you might want to wrap it for debug builds
to let you develop; e.g  with if (!BuildConfig.DEBUG)

:::

```kotlin

// With amazon and google play store configured 

installOriginCheckWithDefaults("com.example.installer").error()

// Without amazon and google play store configured by default 
installOriginCheckWithoutDefaults("com.example.installer").error()

```