package io.github.dllewellyn.safetorun.features.signatureverify

import android.content.Context
import io.github.dllewellyn.safetorun.R
import io.github.dllewellyn.safetorun.reporting.BaseAndroidStrings

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
