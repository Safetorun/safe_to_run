package io.github.dllewellyn.safetorun.features.installorigin

open class InstallOrigin(val originPackage: String)

class GooglePlayStore : InstallOrigin(originPackage = "com.android.vending")
class AmazonStore : InstallOrigin(originPackage = "com.amazon.venezia")
