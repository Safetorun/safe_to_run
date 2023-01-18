package com.safetorun.models.core

data class ConfirmVerificationRequest(
    val signature: String,
    val apiKey: String
)
