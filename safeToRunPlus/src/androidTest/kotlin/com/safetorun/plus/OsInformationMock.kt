package com.safetorun.plus

import android.content.Context
import android.content.pm.InstallSourceInfo
import android.content.pm.PackageManager
import android.os.Build
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import java.lang.reflect.Field
import java.lang.reflect.Modifier


internal const val SDK_INT = 123
internal const val MANUFACTURER = "manufacturer"
internal const val MODEL = "brand"
internal const val BOARD = "board"
internal const val BOOTLOADER = "bootloader"
internal val CPU_ABI = arrayOf("cpuAbi", "cpuAbi2")
internal const val HOST = "host"
internal const val HARDWARE = "hardware"
internal const val DEVICE = "devive"

private val pm = mockk<PackageManager>()


internal fun Context.setupMocks() {
    mockkStatic(Build::class)

    mockBuildField(SDK_INT, "SDK_INT", Build.VERSION::class.java)
    mockBuildField(MANUFACTURER, "MANUFACTURER", Build::class.java)
    mockBuildField(MODEL, "MODEL", Build::class.java)
    mockBuildField(BOARD, "BOARD", Build::class.java)
    mockBuildField(BOOTLOADER, "BOOTLOADER", Build::class.java)
    mockBuildField(CPU_ABI, "SUPPORTED_ABIS", Build::class.java)
    mockBuildField(HOST, "HOST", Build::class.java)
    mockBuildField(HARDWARE, "HARDWARE", Build::class.java)
    mockBuildField(DEVICE, "DEVICE", Build::class.java)

    every { packageManager } returns pm
    every { packageManager.getInstallerPackageName(any()) } returns SharedInstallOrigin.PACKAGE_NAME_RETURNS
    every { packageName } returns "com.anything"
    every { packageManager.getInstallSourceInfo(any()) } returns mockk<InstallSourceInfo>().apply {
        every { installingPackageName } returns SharedInstallOrigin.PACKAGE_NAME_RETURNS
    }
}


private fun <T> mockBuildField(v: T, fieldName: String, clazz: Class<*>) {
    val sdkIntField = clazz.getField(fieldName)
    sdkIntField.isAccessible = true
    Field::class.java.getDeclaredField("modifiers").also {
        it.isAccessible = true
        it.set(sdkIntField, sdkIntField.modifiers and Modifier.FINAL.inv())
    }
    sdkIntField.set(null, v)
}
