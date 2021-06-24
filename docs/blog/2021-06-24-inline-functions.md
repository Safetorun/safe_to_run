---
slug: inline-functions 
title: "Secure against de/recompiling android apps" 
author: Daniel Llewellyn 
author_url: https://github.com/dllewellyn
tags: [security, android, safetorun, inline, decompilation, recompilation, kotlin, inline]
---

### Using kotlin Inline functions to help secure against de/recompiling android apps

When trying to attack an android application, attackers often try to circumvent some of the protections you've
introduced into your app. For example, you might have a signature check added in order to prevent attackers from adding
malware into your app and republishing it:


[Safe to run - signature verification ](https://safetorun.github.io/safe_to_run/docs/signature)

They might also reverse your app to remove any root protection / detection you've added:


[Safe to run - root detection](https://safetorun.github.io/safe_to_run/docs/rootdetection)


Or they might try to remove any other checks you have added, for example, checks to stop it running on an emulator:


[Safe to run - emulator check](https://safetorun.github.io/safe_to_run/docs/emulatorcheck)

In order to make this harder, we can implement these checks using the inline keyword in kotlin. What makes it easy now?
To understand how inlining functions can help, we should first look at how easy it is without inlining. If we take an
application compiled with the 'Safe to run' reporting checks [Safe to run](https://github.com/safetorun/safe_to_run)

### The standard approach

Let's suppose we add this configuration

```kotlin
SafeToRun.init(
    configure {
        osDetectionCheck(banAvdEmulator()).error()
    }
)
```

To our Application or in MainActivity. Then we run one check at launch (in MainActivity onCreate())

```kotlin
if (SafeToRun.isSafeToRun().anyFailures()) {
    throw RuntimeException("Abc")
}
```

And we also add a button to do a check, let's say we have a button like this:

```xml
<Button android:id="@+id/runSensitiveAction"
    android:text="Run sensitive action"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

And program it up like this:

```kotlin
binding.runSensitiveAction.setOnClickListener {
    if (SafeToRun.isSafeToRun().anyFailures()) {
        Toast.makeText(this, "Not safe to run", Toast.LENGTH_LONG).show()
    } else {
        Toast.makeText(this, "Performed sensitive action!!", Toast.LENGTH_LONG).show()
    }
}
```

When you run the application on an emulator, it will throw an exception. So an attacker might try and take the emulator
detection out - let's look at how this application decompiles. We might see something similar to this:

```java
public final void invoke(SafeToRunConfiguration $this$configure) {
    Intrinsics.checkNotNullParameter($this$configure,"$receiver");
    $this$configure.error(OSDetectionCheckKt.osDetectionCheck(this.this$0,C01051.INSTANCE));
}
```

If we can identify this single line and remove it, then we'll be able to recompile having removed the root detection.
Let's demonstrate. I'm using apk tool (full instructions on de/recompiling are a bit out of scope for this article but
I'll add some of the steps for info):


`apktool.sh d app-debug.apk` 

This gives me the files as smali. If I look for the line of code doing the config I'll see this:

```smali
check-cast v1, Lkotlin/jvm/functions/Function1;

invoke-static {v0, v1}, Lio/github/dllewellyn/safetorun/features/rootdetection/RootDetectionConfigKt;->rootDetection(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)Lio/github/dllewellyn/safetorun/checks/SafeToRunCheck;

move-result-object v0

.line 94
invoke-virtual {p1, v0}, Lio/github/dllewellyn/safetorun/SafeToRunConfiguration;->error(Lio/github/dllewellyn/safetorun/checks/SafeToRunCheck;)V
```

if I remove the final line, it will not run .error() and basically remove the check. So let's do that and recompile:


`apktool.sh b -f -d app-debug`

Zipalign the result 

`./zipalign -v 4 app-debug.apk app-debug-aligned.apk` 

Then sign (password is android)

`apksigner sign --ks ~/.android/debug.keystore output.apk` 

Then install `adb install app-debug-aligned.apk` and you'll see it runs. 

Click the 'sensitive button' and the action is performed. 

The issue This was a fairly straight forward set of steps to remove our check, because you've removed the
configuration in a single place, every place that safe to run is called has now been removed. In order to make all of this harder, we'd ideally want to make it so that if we add two
checks, it takes double the effort to remove them (and three checks triple.. etc etc). 

Inlining In SafeToRun 1.0.3 we have introduced a new function which uses inline functions to make de/recompilation harder. 
As a reminder, in usual compilation when you call a function, the compiled code looks similar to your un-compiled code - 
in the sense that it has a reference to that function, and jumps to where that function is in the binary. When we Inline a 
function, the whole function you're calling is copied inside the calling function at compile time. 

### Inlined function 

Let's add this function to MainActivity:

```kotlin
private inline fun canIRun(actionOnFailure: () -> Unit) {
    if (safeToRun(buildSafeToRunCheckList {
            add {
                banAvdEmulatorCheck()
            }
        })()) {
        actionOnFailure()
    }
}
```

One thing to note is that the syntax for the inlined versioning of Safe to Run is still in active development at the
time of writing, be sure to check the documentation for the most up-to-date syntax If also add this block of code into
the button click, and also into onCreate for MainActivity:

```kotlin
canIRun { throw RuntimeException("Error with safe to run") }
```

We'll repeat the compilation and recompilation stage. After a bit of digging, I found something that looks like this:

```smali
.method public bridge synthetic invoke()Ljava/lang/Object;
.locals 1

invoke-virtual {p0}, Lcom/andro/secure/MainActivity$canIRun$$inlined$safeToRun$2;->invoke()Z

move-result v0

invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

move-result-object v0

return-object v0

.end method
```

and I replaced it with this code:

```smali
.method public bridge synthetic invoke()Ljava/lang/Object;
.locals 1

invoke-virtual {p0}, Lcom/andro/secure/MainActivity$canIRun$$inlined$safeToRun$2;->invoke()Z

move-result v0

invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

move-result-object v0

const v0, 0

invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

move-result-object v0

return-object v0
.end method

```

All this does is returns 'false' (i.e. the check returns false in the inlined version we return a boolean indicating
true if a check failed rather than a SafeToRunReport)

If we recompile using the same steps as before, you'll find that the app runs. However, clicking the button will still
cause a RuntimeException. The reason is that the entire functions call chain is duplicated inside the button click. This
adds some size to the binary but now an attacker would need to find the function in every place where you have called '
canIRun' making the job much harder. 


### Conclusions 
In this article we've demonstrated that by using inline functions (for
the entire chain) and no classes etc, it is exponentially more difficult to remove device safety checks the more checks
you add. 

If you were to add a single check at runtime, it would not be any more difficult - however if you litter your
code with Safe to run checks, you will find it harder to decompile and remove those checks. As ever, it's
impossible to have a foolproof way of preventing re/decompiling due to the nature of the problem - but inlining functions
in this way can help make it harder for an attacker
