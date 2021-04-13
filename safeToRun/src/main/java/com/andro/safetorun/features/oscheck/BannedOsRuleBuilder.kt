package com.andro.safetorun.features.oscheck

class BannedOsRuleBuilder {
    var osVersion: Int? = null
}

fun osEqualToOrGreaterThan(versionNumber: Int): BannedOsRule.OsVersion =
    BannedOsRule.OsVersion(Comparators.GreaterThan, versionNumber)
