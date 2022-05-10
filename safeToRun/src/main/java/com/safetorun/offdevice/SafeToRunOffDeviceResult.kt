package com.safetorun.offdevice

/**
 * The result of a safe to run check that was run off device
 *
 * @param signedResult the signed result (a JWT)
 */
data class SafeToRunOffDeviceResult(val signedResult: String)
