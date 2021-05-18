package io.github.dllewellyn.safetorun.api

import io.github.dllewellyn.safetorun.models.models.deviceInformation

val deviceInformation by lazy {
    deviceInformation("5bzdwZ8Drs1AIsmJAx0M37bndOeEkwbv6pI5fjx1") {
        osVersion("31")
        manufacturer("Manufacturer")
        installOrigin("Install origin")
        installedApplication("com.example")
        signature("Abcdef")
        model("model")
    }
}
