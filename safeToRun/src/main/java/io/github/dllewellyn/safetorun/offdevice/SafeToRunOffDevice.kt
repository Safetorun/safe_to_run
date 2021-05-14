package io.github.dllewellyn.safetorun.offdevice

interface SafeToRunOffDevice {
    fun isSafeToRun(callback: (SafeToRunOffDeviceResult) -> Unit)
}
