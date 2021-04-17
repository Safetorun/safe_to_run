package com.andro.safetorun.features.oscheck

import com.andro.safetorun.conditional.Conditional
import com.andro.safetorun.conditional.ConditionalResponse

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
