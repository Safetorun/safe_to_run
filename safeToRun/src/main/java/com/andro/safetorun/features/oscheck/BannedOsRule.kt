package com.andro.safetorun.features.oscheck


enum class Comparators {
    GreaterThan,
    LessThan,
    GreaterThanOrEqualTo,
    LessThanOrEqualTo,
    EqualTo
}

sealed class BannedOsRule {
    data class OsVersion(val comparators: Comparators, val number: Int)
}


infix fun BannedOsRule.and(bannedOsRule: BannedOsRule) {

}