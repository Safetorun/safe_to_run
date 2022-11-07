package com.safetorun.resilienceshared.builders

import com.safetorun.resilienceshared.dto.OSCheckConfiguration
import com.safetorun.resilienceshared.dto.SingleOSCheckConfiguration

/**
 * Build an os check configuration
 */
class OSCheckConfigurationBuilder internal constructor() {
    private val osCheckList = mutableListOf<SingleOSCheckConfiguration>()

    /**
     * add a single check
     *
     * @param bloc os check
     */
    fun singleCheck(bloc: BaseSingleOSCheckBuilder.() -> Unit) {
        osCheckList.add(
            BaseSingleOSCheckBuilder()
                .apply(bloc)
                .build()
        )
    }

    internal fun build(): OSCheckConfiguration {
        return OSCheckConfiguration(osCheckList)
    }
}
