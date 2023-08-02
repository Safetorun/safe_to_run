package com.safetorun.plus

import com.safetorun.plus.builders.deviceInformationBuilder
import java.lang.reflect.Field
import java.lang.reflect.Modifier

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
        isRooted(false)
    }
}

fun <T> mockBuildField(v: T, fieldName: String, clazz: Class<*>) {
    val sdkIntField = clazz.getField(fieldName)
    sdkIntField.isAccessible = true
    Field::class.java.getDeclaredField("modifiers").also {
        it.isAccessible = true
        it.set(sdkIntField, sdkIntField.modifiers and Modifier.FINAL.inv())
    }
    sdkIntField.set(null, v)
}
