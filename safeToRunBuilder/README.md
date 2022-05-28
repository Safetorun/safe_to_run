# Usage

In your root gradle file
```groovy
buildscript {
    dependencies {
        classpath files('safeToRunBuilder/build/libs/safeToRunBuilder-all.jar')
    }
}
```

In your module

```groovy
plugins {
    id "com.safetorun.builder"
}
```

Configure

```groovy
safeToRun {
    configurationPath = "buildSrc/configuration.json" // Required, path to configuration file
    generatedCodePath = "src/main/java/" // Optional, default is src/main/kotlin/
}
```