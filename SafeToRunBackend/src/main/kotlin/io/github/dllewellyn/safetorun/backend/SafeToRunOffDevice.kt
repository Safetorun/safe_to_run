package io.github.dllewellyn.safetorun.backend

import io.github.dllewellyn.safetorun.SafeToRunLogic
import io.github.dllewellyn.safetorun.SafeToRunConfiguration
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport

internal class SafeToRunOffDevice(private val configuration: SafeToRunConfiguration) : SafeToRunLogic {
    override fun isSafeToRun(): SafeToRunReport {
        return configuration.build().canRun()
    }
}
