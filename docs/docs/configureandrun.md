---
id: configureandrun
title: Configure and run
slug: /configureandrun
---


:::tip

Calling safeToRunOffDevice twice with the same API key will 
return the same instance.

:::

## Configuring the backend

In order to change the deployment, open the file: 

`SafeToRunBackend/io/github/dllewellyn/safetorun/backend/safe_to_run_configuration.kt`

Use the `configure` block to configure your deployment. 
Look at the documentation for checks in order to get more
information about the specific checks you want to use

```kotlin
configure {
    blacklistedAppCheck(context) {
        +"com.example.abc"
    }.warn()

    osDetectionCheck(
        context,
        with(osInformation()) {
            conditionalBuilder {
                with(minOsVersion(MIN_OS_VERSION))
                and(notManufacturer("Abc"))
            }
        }
    ).error()

    verifySignatureCheck(context, "Abc").warn()

    installOriginCheckWithDefaults(context).warn()
}
```
Inside your app configure the instance

```kotlin
context.safeToRunOffDevice(
    "<URL>",
    "<API Key>"
)
```

### Run a check

In order to run a check, you have two options. If you are happy
handling your own threading, call:

```kotlin
safeToRun.isSafeToRun()
```

Otherwise, to run automatically on a backend thread

```kotlin
safeToRun.isSafeToRun { result -> 
    // Callback goes here 
}
```

The result is a signed result, this can be decoded using any JWT
library. For example:

```json
{
  "sub": "deviceId",
  "passes": 3,
  "warnings": 1,
  "iss": "5bzdwZ8Drs1AIsmJAx0M37bndOeEkwbv6pI5fjx1",
  "exp": 1621705245,
  "errors": 0
}
```

You should check the following for your JWT

* The certificate matches what you expect
* That the numbers of errors is 0
* ISS Is the API key you used (i.e it is not a different API key)
* Exp (expiry time) has not expired
