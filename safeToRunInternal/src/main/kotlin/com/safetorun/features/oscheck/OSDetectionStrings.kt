package com.safetorun.features.oscheck

/**
 * Generic messages to fail if an os detection fails
 */
interface OSDetectionStrings {
    /**
     * Failure message if an os detection fails but the conditional
     * did not return a message
     */
    fun genericFailureMessage(): String

    /**
     * Message if the OS check passed
     */
    fun genericPassMessage(): String
}
