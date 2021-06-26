---
id: verifyurls
title: URL verification
slug: /verifyurls
---


By default, no URLs are allowed:

```kotlin
"https://safetorun.com".urlVerification {} == false 
```

However, we can add an allowed host:

```kotlin
"https://safetorun.com".urlVerification {
    "safetorun.com".allowHost()
} == true 
```

Or, we can specify an entire URL:

```kotlin
"https://safetorun.com".urlVerification {
    "https://safetorun.com".allowUrl()
} == true 
```

Not recommended - but we can bypass any URL check by allowing
all urls:

```kotlin
"https://safetorun.com".urlVerification {
    allowAnyUrl()
} == true 
```

### Parameters 

By default, no parameters are allowed;

```kotlin
"https://safetorun.com?param=abc".urlVerification {
    "safetorun.com".allowHost()
} == false
```

We can, however add some allowable configuration:

```kotlin
"https://safetorun.com?param=abc".urlVerification {
    "safetorun.com".allowHost()
    allowParameter {
        allowedType = AllowedType.String
        parameterName = "param"
    }
} == true 
```

These allowed types will only allow the correct types to be used as parameters:

```kotlin
"https://safetorun.com?param=abc".urlVerification {
    "safetorun.com".allowHost()
    allowParameter {
        allowedType = AllowedType.Bool
        parameterName = "param"
    }
} == false
```

You can allow very specific URLs if you prefer:

```kotlin
"https://safetorun.com?param=abc".urlVerification {
    "https://safetorun.com?param=abc".allowUrl()
} == true 
```

Or, you can bypass the whole check for parameters (not recommended):

```kotlin
"https://safetorun.com?param=abc".urlVerification {
    "safetorun.com".allowHost()
    allowAnyParameter()
} == true
```

Any URL will also allow parameters:

```kotlin
"https://safetorun.com?param=abc".urlVerification {
    allowAnyUrl()
} == true
