package io.github.dllewellyn.safetorun.features.installorigin

open class InstallOrigin(val originPackage: String) {
    override fun hashCode(): Int {
        return super.hashCode() * originPackage.hashCode()
    }

    override fun toString(): String {
        return originPackage
    }

    override fun equals(other: Any?): Boolean {
        if (other !is InstallOrigin)
            return false
        return originPackage == other.originPackage
    }
}

class GooglePlayStore : InstallOrigin(originPackage = "com.android.vending")
class AmazonStore : InstallOrigin(originPackage = "com.amazon.venezia")