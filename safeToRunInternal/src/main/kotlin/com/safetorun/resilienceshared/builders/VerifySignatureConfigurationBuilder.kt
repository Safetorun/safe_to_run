package com.safetorun.resilienceshared.builders

import com.safetorun.resilienceshared.dto.VerifySignatureConfiguration

/**
 * Adding verify signature
 */
interface VerifySignatureConfigurationBuilder {
    /**
     * Add allowed signature
     */
    operator fun String.unaryPlus()

    /**
     * Add allowed signature
     */
    fun String.allowSignature()
}

/**
 * Verify signature configuration
 */
class BaseVerifySignatureConfigurationBuilder internal constructor() :
    VerifySignatureConfigurationBuilder {

    private val allowedSignatures = mutableListOf<String>()

    /**
     * Add allowed signature
     */
    override operator fun String.unaryPlus() {
        allowedSignatures.add(this)
    }

    /**
     * Add allowed signature
     */
    override fun String.allowSignature() {
        allowedSignatures.add(this)
    }

    internal fun build(): VerifySignatureConfiguration {
        return VerifySignatureConfiguration(allowedSignatures)
    }
}
