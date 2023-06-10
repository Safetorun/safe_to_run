package com.safetorun

import android.content.Context
import android.content.pm.InstallSourceInfo
import android.content.pm.PackageManager
import android.os.Build
import com.safetorun.features.installorigin.SharedInstallOrigin
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import java.lang.reflect.Field
import java.lang.reflect.Modifier


internal const val SdkInt = 123
internal const val Manufacturer = "manufacturer"
internal const val Model = "brand"
internal const val Board = "board"
internal const val BootLoader = "bootloader"
internal val CpuAbi = arrayOf("cpuAbi", "cpuAbi2")
internal const val Host = "host"
internal const val Hardware = "hardware"
internal const val Device = "devive"

private val pm = mockk<PackageManager>()


internal fun Context.setupMocks() {
    mockkStatic(Build::class)

    mockBuildField(SdkInt, "SDK_INT", Build.VERSION::class.java)
    mockBuildField(Manufacturer, "MANUFACTURER", Build::class.java)
    mockBuildField(Model, "MODEL", Build::class.java)
    mockBuildField(Board, "BOARD", Build::class.java)
    mockBuildField(BootLoader, "BOOTLOADER", Build::class.java)
    mockBuildField(CpuAbi, "SUPPORTED_ABIS", Build::class.java)
    mockBuildField(Host, "HOST", Build::class.java)
    mockBuildField(Hardware, "HARDWARE", Build::class.java)
    mockBuildField(Device, "DEVICE", Build::class.java)

    every { packageManager } returns pm
    every { packageManager.getInstallerPackageName(any()) } returns SharedInstallOrigin.PACKAGE_NAME_RETURNS
    every { packageName } returns "com.anything"
    every { packageManager.getInstallSourceInfo(any()) } returns mockk<InstallSourceInfo>().apply {
        every { installingPackageName } returns SharedInstallOrigin.PACKAGE_NAME_RETURNS
    }

    every { resources } returns SharedInstallOrigin.setupAMockResources()
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
