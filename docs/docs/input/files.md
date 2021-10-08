---
id: files 
title: File verification 
slug: /filesverification
---

### Verify files

Imagine you have written code that looks like this:

```kotlin
if (intent.action == Intent.ACTION_SEND) {
    val uri = intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as Uri
    sendData(contentResolver.openInputStream(uri))
}
```

Your application in this case might be at risk of an attack that looks like this:

```kotlin
Intent(Intent.ACTION_SEND).apply {
    putExtra(
        Intent.EXTRA_STREAM,
        Uri.parse("file:///data/data/${it.packageName}/db/sensitive.db")
    )
}
```

In this example, we might allow an attacker to send sensitive keys, databases (etc).

### Allow no private apps

To prevent this type of attack, we can use the verify command in order to check a file
(or a URI) before opening it.

```kotlin
val isFileSafeToOpen = uri.verifyFile(this) {}
```

By default, no files from your private directory is allowed - which is what you want in most cases.

### Allow specific private file

We can allow a specific file

```kotlin
val isFileSafeToOpen = uri.verifyFile(this) {
    // This
    File(context.filesDir + "files/", "safe_to_read.txt").allowExactFile()

    // Is the same as this:
    addAllowedExactFile(File(context.filesDir + "files/", "safe_to_read.txt"))
}
```

### Allow all files in a directory

Instead of this, we can add a directory and allow all files in that directory

```kotlin
val isFileSafeToOpen = uri.verifyFile(this) {
    // This
    addAllowedParentDirectory(context.filesDir.allowDirectory())

    // Is the same as this:
    FileUriMatcherBuilder.FileUriMatcherCheck(
        context.filesDir,
        false
    )
}
```

### Allow all files and subdirectories

At the moment `/data/data/com.safe.to.run/files/abc.txt` would be allowed,
but `/data/data/com.safe.to.run/files/subdir/abc.txt`
would not. To allow subdirectories:

```kotlin
val isFileSafeToOpen = uri.verifyFile(this) {
    // This
    addAllowedParentDirectory(context.filesDir.allowDirectoryAndSubdirectories())

    // Is the same as this:
    FileUriMatcherBuilder.FileUriMatcherCheck(
        context.filesDir,
        true
    )
}
```

### Allow any file

We would not recommend doing this:

```kotlin
val isFileSafeToOpen = uri.verifyFile(this) {
    allowAnyFile = true
}
```
