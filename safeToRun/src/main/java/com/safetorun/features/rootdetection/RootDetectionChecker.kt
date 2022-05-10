package com.safetorun.features.rootdetection

internal interface RootDetectionChecker {
    fun isRooted(): Boolean
    fun isRootedWithBusyBoxCheck(): Boolean
}
