package com.andro.safetorun.features.rootdetection

interface RootDetectionChecker {
    fun isRooted(): Boolean
    fun isRootedWithBusyBoxCheck(): Boolean
}