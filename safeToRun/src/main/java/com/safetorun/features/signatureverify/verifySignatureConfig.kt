package com.safetorun.features.signatureverify

import android.content.Context
import android.os.Build

/**
 * Verify signature configuration override SDK version
 * @param signature signatures to allow
 */
inline fun Context.verifySignatureCheck(
    vararg signature: String
) = getAppSignature()?.string()?.let {
    signature.toList().contains(it).not()
} ?: true

/**
 * Verify signature configuration override SDK version
 * @param signature signatures to allow
 */
inline fun Context.verifySignatureCheckOverrideVersion(
    versionNumber: Int = Build.VERSION.SDK_INT,
    vararg signature: String
) = getAppSignature(versionNumber)?.string()?.let {
    signature.toList().contains(it).not()
} ?: true
