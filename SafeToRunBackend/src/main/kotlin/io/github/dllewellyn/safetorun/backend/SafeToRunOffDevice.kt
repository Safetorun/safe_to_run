package io.github.dllewellyn.safetorun.backend

import io.github.dllewellyn.safetorun.SafeToRun
import io.github.dllewellyn.safetorun.SafeToRunConfiguration
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport

internal class SafeToRunOffDevice(private val configuration: SafeToRunConfiguration) : SafeToRun {
    override fun isSafeToRun(): SafeToRunReport {
        return configuration.build().canRun()
    }
}
