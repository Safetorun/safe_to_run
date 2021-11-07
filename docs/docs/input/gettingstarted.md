---
id: gettingstarted 
title: Getting started
slug: /gettingstarted
---

Gradle:

```groovy
implementation "io.github.dllewellyn.safetorun:inputverification:$latest_version"
```

Sample for URL verification

```kotlin
val url = intent?.extras?.getString("url")
val isUrlOk = url?.urlVerification {
    "safetorun.com".allowHost()
    allowParameter {
        allowedType = AllowedType.String
        parameterName = "a_token"
    }
}

if (isUrlOk != true) {
    throw RuntimeException("Failed validation!!")
}
```

And a sample for intent verification

```kotlin

intent.verify {
    urlConfiguration { "safetorun.com".allowHost() }

    actionOnSuccess = {
        intent?.extras?.getString("url")
            ?.let { "${it}?sensitive_token=pleasekeepmesecret" }
            ?.let(webView::loadUrl)
    }
}
```
