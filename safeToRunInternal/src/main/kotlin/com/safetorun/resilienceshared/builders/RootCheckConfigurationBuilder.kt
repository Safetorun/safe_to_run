package com.safetorun.resilienceshared.builders

import com.safetorun.resilienceshared.dto.RootCheckConfiguration

/**
 * Builder for root check configuration
 */
class RootCheckConfigurationBuilder internal constructor() {

    /**
     * Whether or not to allow busybox
     */
    var tolerateBusyBox = false

    internal fun build() = RootCheckConfiguration(tolerateBusyBox)
}
