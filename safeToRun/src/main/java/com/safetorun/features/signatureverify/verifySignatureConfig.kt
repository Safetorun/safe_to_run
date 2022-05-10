package com.safetorun.features.signatureverify

import android.content.Context
import android.os.Build
import com.safetorun.checks.SafeToRunCheck

/**
 * Verify the signature configuration. Can take multiple signatures for different
 * signatures
 *
 * @receiver Android context
 *
 * @param signature one or more signatures that are acceptable
 */
fun Context.verifySignatureConfig(vararg signature: String): SafeToRunCheck {
    return verifySignatureConfiguration(
        signatureVerificationStrings = AndroidSignatureVerificationStringsImpl(this),
        signatureVerificationQuery = { getAppSignature()?.string() },
        *signature
    )
}

/**
 * Verify signature configuration override SDK version
 *
 * @param sdkVersion the sdk version
 * @param signature signatures to allow
 */
internal fun Context.verifySignatureConfigOverrideSdkVersion(
    sdkVersion: Int,
    vararg signature: String
): SafeToRunCheck {
    return verifySignatureConfiguration(
        AndroidSignatureVerificationStringsImpl(this),
        { getAppSignature(sdkVersion)?.string() },
        *signature
    )
}


/**
 * Verify signature configuration override SDK version
 * @param signature signatures to allow
 */
inline fun Context.verifySignatureCheck(
    vararg signature: String
) = getAppSignature()?.string()?.let {
    signature.toList().contains(it).not()
} ?: true

/**
 * Verify signature configuration override SDK version
 * @param signature signatures to allow
 */
inline fun Context.verifySignatureCheckOverrideVersion(
    versionNumber: Int = Build.VERSION.SDK_INT,
    vararg signature: String
) = getAppSignature(versionNumber)?.string()?.let {
    signature.toList().contains(it).not()
} ?: true
