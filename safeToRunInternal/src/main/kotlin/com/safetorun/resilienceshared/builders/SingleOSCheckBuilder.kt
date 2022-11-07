package com.safetorun.resilienceshared.builders

import com.safetorun.resilienceshared.dto.CheckComparator
import com.safetorun.resilienceshared.dto.IntCheckType
import com.safetorun.resilienceshared.dto.StringCheckType

/**
 * Single OS Check builder
 */
interface SingleOSCheckBuilder {
    /**
     * Add a single check to the list of all checks
     */
    fun IntCheckType.failIf(value: Int, comparator: CheckComparator)

    /**
     * Add a single check to the list of all checks
     */
    fun StringCheckType.failIf(value: String, comparator: CheckComparator)

    /**
     * Add a single check to the list of all checks
     */
    fun IntCheckType.unless(value: Int, comparator: CheckComparator)

    /**
     * Add a single check to the list of all checks
     */
    fun StringCheckType.unless(value: String, comparator: CheckComparator)
}
