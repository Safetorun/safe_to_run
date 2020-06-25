package com.andro.safetorun.features.rootdetection

interface RootDetection {
    fun isRooted(): Boolean
    fun isRootedIgnoringBusyBox(): Boolean
}

