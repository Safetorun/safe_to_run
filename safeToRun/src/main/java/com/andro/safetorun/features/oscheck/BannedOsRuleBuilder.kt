package com.andro.safetorun.features.oscheck

import com.andro.safetorun.features.rootdetection.RootBeerRootDetection

class BannedOsRuleBuilder {
    var osVersion: Int? = null
}

fun osEqualToOrGreaterThan(versionNumber: Int): BannedOsRule.OsVersion =
    BannedOsRule.OsVersion(Comparators.GreaterThan, versionNumber)
