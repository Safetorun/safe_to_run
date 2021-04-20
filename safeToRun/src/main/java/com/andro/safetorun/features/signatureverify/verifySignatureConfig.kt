package com.andro.safetorun.features.signatureverify

import android.content.Context
import com.andro.safetorun.checks.SafeToRunCheck

fun Context.verifySignatureConfig(vararg signature: String): SafeToRunCheck {
    return SignatureVerificationCheck(
        signature.toList(),
        signatureVerificationStrings = AndroidSignatureVerificationStringsImpl(this),
        context = this
    )
}

fun Context.verifySignatureConfigOverrideSdkVersion(sdkVersion: Int, vararg signature: String): SafeToRunCheck {
    return SignatureVerificationCheck(
        signature.toList(), sdkVersion, AndroidSignatureVerificationStringsImpl(this),
        this
    )
}