package com.andro.safetorun.features.oscheck

import com.andro.safetorun.conditional.Conditional

class MinOSVersionRule(private val minOSVersion: Int, private val osInformationQuery: OSInformationQuery) :
    Conditional {

    override fun invoke(): Boolean {
        return osInformationQuery.osVersion() >= minOSVersion
    }
}

fun OSInformationQuery.minOsVersion(minOSVersion: Int) = MinOSVersionRule(minOSVersion, this)
