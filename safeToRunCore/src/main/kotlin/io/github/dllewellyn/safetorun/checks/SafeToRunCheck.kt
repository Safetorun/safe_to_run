package io.github.dllewellyn.safetorun.checks

import io.github.dllewellyn.safetorun.reporting.SafeToRunReport

interface SafeToRunCheck {
    fun canRun(): SafeToRunReport
}
