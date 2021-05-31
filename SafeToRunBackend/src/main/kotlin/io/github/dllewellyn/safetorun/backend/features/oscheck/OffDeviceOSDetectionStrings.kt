package io.github.dllewellyn.safetorun.backend.features.oscheck

import io.github.dllewellyn.safetorun.features.oscheck.OSDetectionStrings
import io.micronaut.context.annotation.Value
import javax.inject.Singleton

@Singleton
internal class OffDeviceOSDetectionStrings(
    @Value("\${safe.to.run.os_detection_check_generic_failure}")
    private val genericFailureMessage: String,
    @Value("\${safe.to.run.os_detection_check_generic_pass}")
    private val genericPassMessage: String
) : OSDetectionStrings {

    override fun genericFailureMessage(): String {
        return genericFailureMessage
    }

    override fun genericPassMessage(): String {
        return genericPassMessage
    }
}
