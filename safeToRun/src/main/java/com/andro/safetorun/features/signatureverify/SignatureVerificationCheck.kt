package com.andro.safetorun.features.signatureverify

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Build
import android.util.Base64
import com.andro.safetorun.checks.SafeToRunCheck
import com.andro.safetorun.reporting.SafeToRunReport
import java.security.MessageDigest

class SignatureVerificationCheck(
    private val expectedSignature: List<String>,
    private val sdkVersion: Int = Build.VERSION_CODES.P,
    private val signatureVerificationStrings: SignatureVerificationStrings,
    private val context : Context
) : SafeToRunCheck {

    override fun canRun(): SafeToRunReport {
        return context.getAppSignature()?.string()?.let { currentSignature ->
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

    @SuppressLint("NewApi", "PackageManagerGetSignatures")
    private fun Context.getAppSignature(): Signature? = if (sdkVersion < 28) {
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

    private fun Signature.string(): String? = try {
        val signatureBytes = toByteArray()
        val digest = MessageDigest.getInstance("SHA")
        val hash = digest.digest(signatureBytes)
        Base64.encodeToString(hash, Base64.NO_WRAP)
    } catch (exception: Exception) {
        null
    }


    companion object {
        private const val SIGNATURE_NOT_FOUND = "sg-nf"
        private const val SIGNATURE_NOT_MATCH = "sg-nf"
    }
}