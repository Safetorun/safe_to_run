package io.github.dllewellyn.safetorun.features.signatureverify

import android.content.pm.Signature
import android.util.Base64
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import java.security.MessageDigest

internal class SignatureVerificationCheck(
    private val expectedSignature: List<String>,
    private val signatureVerificationStrings: SignatureVerificationStrings,
    private val signatureVerificationQuery: SignatureVerificationQuery
) : SafeToRunCheck {

    override fun canRun(): SafeToRunReport {
        return signatureVerificationQuery.retrieveSignatureForApplication()?.let { currentSignature ->
            if (expectedSignature.contains(currentSignature)) {
                SafeToRunReport.SafeToRunReportSuccess(signatureVerificationStrings.signatureMatchesMessage())
            } else {
                SafeToRunReport.SafeToRunReportFailure(
                    SIGNATURE_NOT_MATCH,
                    signatureVerificationStrings.signatureNotMatchedMessage(currentSignature)
                )
            }
        } ?: SafeToRunReport.SafeToRunReportFailure(
            SIGNATURE_NOT_FOUND,
            signatureVerificationStrings.signatureNotFoundMessage()
        )
    }

    companion object {
        private const val SIGNATURE_NOT_FOUND = "sg-nf"
        private const val SIGNATURE_NOT_MATCH = "sg-nf"
    }
}