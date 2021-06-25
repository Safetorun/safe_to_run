---
id: intentverification title: Intent verification slug: /intentverification
---


:::tip

Intent verification is currently still in Beta! Please provide feedback as an
issue [here](https://github.com/safetorun/safe_to_run)

:::

Intent verification is intended to provide a simple interface for you to protect against attacks on android 'Intents'.
The types of vulnerabilities are often complex and subtle.

### The basics

The basics of the Safe to run intent verification service is to call `.verify` on any intent.

For example:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_bouncable)

    // Either do
    if (intent.verify {

        }) {
        // Do something 
    } else {
        // Report failure
    }

    // Or instead you can do 

    intent.verify {
        actionOnSuccess = {
            // Do something
        }

        actionOnFailure = {
            // Report failure
        }
    }
}
```

Verify is locked down by default to disallow any URLs, and does not
allow any 'containing' intents - that is, any intents within the bundle

### Opening URLs

By default, a bundle cannot contain any urls:

```kotlin
val intent = Intent().apply {
    putStringExtra("url", "https://abc.com")
}

val result : Boolean = intent.verify { }
// Equals false
```

If you want to allow a specific host, you can do this:

```kotlin
val intent = Intent().apply {
    putStringExtra("url", "https://abc.com?abc=def")
}

val result : Boolean = intent.verify {
    "https://abc.com?abc=def".allowUrl()
}
// Equals true 
```

The next best thing, is to white list the host:

```kotlin
val intent = Intent().apply {
    putStringExtra("url", "https://abc.com?abc=def")
}

val result : Boolean = intent.verify {
    "abc.com".allowHost()
}
// Equals true 
```

The downside of this approach being that you're no longer entirely
sure of the specific parameters that may be passed to your URL

The least recommended option is to allow all urls:

```kotlin
val intent = Intent().apply {
    putStringExtra("url", "https://abc.com")
}

val result : Boolean = intent.verify {
    allowAnyUrls = true
}
// Equals true 
```

