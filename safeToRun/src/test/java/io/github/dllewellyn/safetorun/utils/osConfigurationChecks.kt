package io.github.dllewellyn.safetorun.utils

import java.lang.reflect.Field
import java.lang.reflect.Modifier

internal fun <T> mockBuildField(v: T, fieldName: String, clazz: Class<*>) {
    val sdkIntField = clazz.getField(fieldName)
    sdkIntField.isAccessible = true
    Field::class.java.getDeclaredField("modifiers").also {
        it.isAccessible = true
        it.set(sdkIntField, sdkIntField.modifiers and Modifier.FINAL.inv())
    }
    sdkIntField.set(null, v)
}
