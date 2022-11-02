package com.safetorun.backendresilience.builders

import com.safetorun.backendresilience.dto.SingleOSCheckConfigurationOffDevice
import com.safetorun.resilienceshared.dto.CheckComparator
import com.safetorun.resilienceshared.dto.IntCheckType
import com.safetorun.resilienceshared.dto.Severity
import com.safetorun.resilienceshared.dto.SingleIntCheck
import com.safetorun.resilienceshared.dto.SingleStringCheck
import com.safetorun.resilienceshared.dto.StringCheckType

/**
 * Build a single os check
 */
class SingleOSCheckBuilderOffDevice internal constructor(val severity: Severity) {
    private val allStringChecks: MutableList<SingleStringCheck> = mutableListOf()
    private val allIntChecks: MutableList<SingleIntCheck> = mutableListOf()

    private val allIntUnlessChecks: MutableList<SingleIntCheck> = mutableListOf()
    private val allStringUnlessChecks: MutableList<SingleStringCheck> = mutableListOf()

    /**
     * Add a single check to the list of all checks
     */
    fun IntCheckType.failIf(value: Int, comparator: CheckComparator) {
        allIntChecks.add(
            SingleIntCheck(
                intValue = value,
                checkType = this,
                comparator = comparator
            )
        )
    }

    /**
     * Add a single check to the list of all checks
     */
    fun StringCheckType.failIf(value: String, comparator: CheckComparator) {
        allStringChecks.add(
            SingleStringCheck(
                stringValue = value,
                checkType = this,
                comparator = comparator
            )
        )
    }

    /**
     * Add a single check to the list of all checks
     */
    fun IntCheckType.unless(value: Int, comparator: CheckComparator) {
        allIntUnlessChecks.add(
            SingleIntCheck(
                intValue = value,
                checkType = this,
                comparator = comparator
            )
        )
    }

    /**
     * Add a single check to the list of all checks
     */
    fun StringCheckType.unless(value: String, comparator: CheckComparator) {
        allStringUnlessChecks.add(
            SingleStringCheck(
                stringValue = value,
                checkType = this,
                comparator = comparator
            )
        )
    }

    internal fun build() =
        SingleOSCheckConfigurationOffDevice(
            allIntChecks = allIntChecks,
            severity = severity,
            allStringChecks = allStringChecks,
            unlessIntChecks = allIntUnlessChecks,
            unlessStringChecks = allStringUnlessChecks,
        )
}
