package io.github.dllewellyn.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.conditional.ConditionalResponse

internal class MinOSVersionRule(private val minOSVersion: Int, private val osInformationQuery: OSInformationQuery) :
    Conditional {

    override fun invoke(): ConditionalResponse {
        return if (osInformationQuery.osVersion() >= minOSVersion) {
            ConditionalResponse(false)
        } else {
            ConditionalResponse(true, "${osInformationQuery.osVersion()} == $minOSVersion")
        }
    }
}

internal fun OSInformationQuery.minOsVersion(minOSVersion: Int): Conditional = MinOSVersionRule(minOSVersion, this)
