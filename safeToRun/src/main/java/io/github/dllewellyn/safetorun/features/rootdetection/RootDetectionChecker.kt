package io.github.dllewellyn.safetorun.features.rootdetection

interface RootDetectionChecker {
    fun isRooted(): Boolean
    fun isRootedWithBusyBoxCheck(): Boolean
}