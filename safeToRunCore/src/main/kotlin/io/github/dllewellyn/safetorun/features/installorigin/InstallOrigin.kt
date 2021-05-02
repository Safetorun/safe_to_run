package io.github.dllewellyn.safetorun.features.installorigin

open class InstallOrigin(val originPackage: String) {
    override fun toString(): String {
        return originPackage
    }
}

class GooglePlayStore : InstallOrigin(originPackage = "com.android.vending")
class AmazonStore : InstallOrigin(originPackage = "com.amazon.venezia")