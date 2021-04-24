package io.github.dllewellyn.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.conditional.ConditionalResponse

class MinOSVersionRule(private val minOSVersion: Int, private val osInformationQuery: OSInformationQuery) :
    Conditional {

    override fun invoke(): ConditionalResponse {
        if (osInformationQuery.osVersion() >= minOSVersion) {
            return ConditionalResponse(false)
        } else {
            return ConditionalResponse(true, "${osInformationQuery.osVersion()} == $minOSVersion")
        }
    }
}

fun OSInformationQuery.minOsVersion(minOSVersion: Int) = MinOSVersionRule(minOSVersion, this)
