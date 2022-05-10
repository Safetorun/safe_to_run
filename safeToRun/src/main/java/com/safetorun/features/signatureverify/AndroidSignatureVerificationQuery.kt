package com.safetorun.features.signatureverify

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Build
import android.util.Base64
import java.security.MessageDigest

internal class AndroidSignatureVerificationQuery(
    private val context: Context,
    private val sdkVersion: Int = Build.VERSION_CODES.P,
) :
    SignatureVerificationQuery {

    override fun retrieveSignatureForApplication(): String? {
        return context.getAppSignature(sdkVersion)?.string()
    }
}

@Suppress("SwallowedException")
/**
 * Get app signature
 *
 * @param sdkVersion the version of the sdk
 */
inline fun Context.getAppSignature(sdkVersion: Int = Build.VERSION_CODES.P): Signature? = try {
    retrieveSignature(sdkVersion)
} catch (exception: NoSuchFieldError) {
    null
}

@SuppressLint("NewApi", "PackageManagerGetSignatures")
/**
 * Retrieve the applications signature
 *
 * @param sdkVersion the version of the sdk
 */
inline fun Context.retrieveSignature(sdkVersion: Int) =
    if (sdkVersion < Build.VERSION_CODES.P) {
        packageManager.getPackageInfo(
            packageName,
            PackageManager.GET_SIGNATURES
        ).signatures.firstOrNull()
    } else {
        packageManager.getPackageInfo(
            packageName,
            PackageManager.GET_SIGNING_CERTIFICATES
        ).signingInfo.apkContentsSigners.firstOrNull()
    }

/**
 * Convert a signature to a string
 *
 * @return a string representation of the signature
 */
inline fun Signature.string(): String? {
    val signatureBytes = toByteArray() ?: return null
    val digest = MessageDigest.getInstance("SHA")
    val hash = digest.digest(signatureBytes)
    return Base64.encodeToString(hash, Base64.NO_WRAP)
}
