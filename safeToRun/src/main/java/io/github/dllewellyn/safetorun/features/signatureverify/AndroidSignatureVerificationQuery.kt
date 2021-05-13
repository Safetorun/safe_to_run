package io.github.dllewellyn.safetorun.features.signatureverify

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Build
import android.util.Base64
import java.lang.NullPointerException
import java.security.MessageDigest

class AndroidSignatureVerificationQuery(
    private val context: Context,
    private val sdkVersion: Int = Build.VERSION_CODES.P,
) :
    SignatureVerificationQuery {

    override fun retrieveSignatureForApplication(): String? {
        return context.getAppSignature()?.string()
    }

    @SuppressLint("NewApi", "PackageManagerGetSignatures")
    private fun Context.getAppSignature(): Signature? = if (sdkVersion < Build.VERSION_CODES.P) {
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

    private fun Signature.string(): String? {
        val signatureBytes = toByteArray() ?: return null
        val digest = MessageDigest.getInstance("SHA")
        val hash = digest.digest(signatureBytes)
        return Base64.encodeToString(hash, Base64.NO_WRAP)
    }
}
