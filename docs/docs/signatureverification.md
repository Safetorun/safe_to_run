---
id: signatureverification
title: Signature verification
slug: /signature
---

In order to configure:


```kotlin
verifySignatureConfig("cSP1O3JN/8+Ag14WAOeOEnwAnpY=").error()
```

To generate your signature, the simplest way is to simply run the report and read
the result, for example


```kotlin
Log.v("Signature", verifySignatureConfig("").canRun())
```

Argument can take multiple strings so you can provide a signature for
debug, release (etc) builds