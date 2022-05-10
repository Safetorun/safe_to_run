package com.safetorun.api

import com.safetorun.models.builders.deviceInformationBuilder

val deviceInformation by lazy {
    deviceInformationBuilder("5bzdwZ8Drs1AIsmJAx0M37bndOeEkwbv6pI5fjx1") {
        osVersion("31")
        manufacturer("Manufacturer")
        installOrigin("Install origin")
        installedApplication("com.example")
        signature("Abcdef")
        model("model")
        board("board")
        bootloader("bootloader")
        device("device")
        hardware("hardware")
        host("host")
        cpuAbi("cpu")
        cpuAbi("cpuAbi")
    }
}
