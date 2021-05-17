package io.github.dllewellyn.safetorun.offdevice

interface SafeToRunOffDevice {
    /**
     * Run an async is safe to run command, this will execute
     * off the main UI Thread
     *
     * @param callback function to call on completion
     * @return result which contains a JWT token with the results of a device check
     */
    fun isSafeToRun(callback: (SafeToRunOffDeviceResult) -> Unit)

    /**
     * Run a synchronous command. Do not call from the main UI Thread
     *
     * * @return result which contains a JWT token with the results of a device check
     */
    fun isSafeToRun(): SafeToRunOffDeviceResult
}
