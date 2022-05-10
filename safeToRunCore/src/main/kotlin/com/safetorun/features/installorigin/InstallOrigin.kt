package com.safetorun.features.installorigin

/**
 * Installation origin (i.e. the app store that it was installed from)
 *
 * @param originPackage the package of the installing application
 */
open class InstallOrigin(val originPackage: String)

/**
 * Install origin for the google play store
 */
class GooglePlayStore : InstallOrigin(originPackage = "com.android.vending")

/**
 * Install origin for the amazon app store
 */
class AmazonStore : InstallOrigin(originPackage = "com.amazon.venezia")
