# Blacklisting documentation

For full information see here:

https://developer.android.com/training/package-visibility/declaring

You'll need to declare android permissions, for example, the suggested way of doing this is by specifying your packages,
e.g.

```xml

<queries>
    <package android:name="com.abc.def"/>
</queries>
```

Or, declare all. That's not suggested

```xml
<uses-permission android:name="android.permission.QUERY_ALL_PACKAGES"/>
```

Disallow an application on device

```kotlin
 blacklistConfiguration(blacklistedAppCheck) {
    +"com.abc.def"
}
```