package io.github.dllewellyn.safetorun.features.signatureverify

import android.content.Context
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

/**
 * Verify the signature configuration. Can take multiple signatures for different
 * signatures
 *
 * @receiver Android context
 *
 * @param signature one or more signatures that are acceptable
 */
fun Context.verifySignatureConfig(vararg signature: String): SafeToRunCheck {
    return SignatureVerificationCheck(
        signature.toList(),
        signatureVerificationStrings = AndroidSignatureVerificationStringsImpl(this),
        signatureVerificationQuery = AndroidSignatureVerificationQuery(this)
    )
}

internal fun Context.verifySignatureConfigOverrideSdkVersion(
    sdkVersion: Int,
    vararg signature: String
): SafeToRunCheck {
    return SignatureVerificationCheck(
        signature.toList(),
        AndroidSignatureVerificationStringsImpl(this),
        AndroidSignatureVerificationQuery(this, sdkVersion)
    )
}
