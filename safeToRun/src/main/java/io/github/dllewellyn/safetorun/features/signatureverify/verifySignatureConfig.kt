package io.github.dllewellyn.safetorun.features.signatureverify

import android.content.Context
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

fun Context.verifySignatureConfig(vararg signature: String): SafeToRunCheck {
    return SignatureVerificationCheck(
        signature.toList(),
        signatureVerificationStrings = AndroidSignatureVerificationStringsImpl(this),
        context = this
    )
}

internal fun Context.verifySignatureConfigOverrideSdkVersion(sdkVersion: Int, vararg signature: String): SafeToRunCheck {
    return SignatureVerificationCheck(
        signature.toList(), sdkVersion, AndroidSignatureVerificationStringsImpl(this),
        this
    )
}