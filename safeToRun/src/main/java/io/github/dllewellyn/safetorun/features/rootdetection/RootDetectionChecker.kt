package io.github.dllewellyn.safetorun.features.rootdetection

internal interface RootDetectionChecker {
    fun isRooted(): Boolean
    fun isRootedWithBusyBoxCheck(): Boolean
}
