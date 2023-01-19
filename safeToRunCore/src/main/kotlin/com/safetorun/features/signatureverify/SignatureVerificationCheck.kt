package com.safetorun.features.signatureverify

import com.safetorun.checks.SafeToRunCheck
import com.safetorun.reporting.SafeToRunReport

internal class SignatureVerificationCheck(
    private val expectedSignature: List<String>,
    private val signatureVerificationStrings: SignatureVerificationStrings,
    private val signatureVerificationQuery: SignatureVerificationQuery
) : SafeToRunCheck {

    override fun canRun(): SafeToRunReport {
        return signatureVerificationQuery.retrieveSignatureForApplication().let { currentSignature ->
            if (expectedSignature.contains(currentSignature)) {
                SafeToRunReport.SafeToRunReportSuccess(signatureVerificationStrings.signatureMatchesMessage())
            } else {
                SafeToRunReport.SafeToRunReportFailure(
                    SIGNATURE_NOT_MATCH,
                    signatureVerificationStrings.signatureNotMatchedMessage(currentSignature)
                )
            }
        }
    }

    companion object {
        private const val SIGNATURE_NOT_FOUND = "sg-nf"
        private const val SIGNATURE_NOT_MATCH = "sg-nf"
    }
}
