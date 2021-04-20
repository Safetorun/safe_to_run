package com.andro.safetorun.features.signatureverify

import android.content.Context
import com.andro.safetorun.R
import com.andro.safetorun.reporting.BaseAndroidStrings

internal class AndroidSignatureVerificationStringsImpl(context: Context) : BaseAndroidStrings(context),
    SignatureVerificationStrings {

    override fun signatureMatchesMessage(): String {
        return resources.getString(R.string.signature_match)
    }

    override fun signatureNotFoundMessage(): String {
        return resources.getString(R.string.signature_not_found)
    }

    override fun signatureNotMatchedMessage(actualSignature: String): String {
        return resources.getString(R.string.no_signature_match, actualSignature)
    }
}