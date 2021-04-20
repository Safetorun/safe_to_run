package com.andro.safetorun.features.signatureverify

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Build
import android.util.Base64
import com.andro.safetorun.R
import com.andro.safetorun.checks.SafeToRunCheck
import com.andro.safetorun.reporting.SafeToRunReport
import java.security.MessageDigest

class SignatureVerificationCheck(
    private val expectedSignature: List<String>,
    private val sdkVersion: Int = Build.VERSION_CODES.P
) : SafeToRunCheck {

    override fun canRun(context: Context): SafeToRunReport {
        return context.getAppSignature()?.string()?.let { currentSignature ->
            if (expectedSignature.contains(currentSignature)) {
                SafeToRunReport.SafeToRunReportSuccess(context.resources.getString(R.string.signature_match))
            } else {
                SafeToRunReport.SafeToRunReportFailure(
                    SIGNATURE_NOT_MATCH,
                    context.resources.getString(R.string.no_signature_match, currentSignature)
                )
            }
        } ?: SafeToRunReport.SafeToRunReportFailure(
            SIGNATURE_NOT_FOUND,
            context.resources.getString(R.string.signature_not_found)
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