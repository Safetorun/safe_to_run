package com.safetorun.plus.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * The result of a safe to run check returning
 *
 * @param correctIssuer was the issuer correct (i.e. is the API key the same one that signed it as was
 * passed into the request)
 * @param anyFailures are there any failures to report for the check
 * @param correctSignature was the signature correct - i.e. was the signature signed with the correct key
 * @param expired has the token expired perhaps indicating a reply attack
 */
data class VerifierResult(
    val correctIssuer: Boolean,
    val anyFailures: Boolean,
    val correctSignature: Boolean,
    val expired: Boolean
)
