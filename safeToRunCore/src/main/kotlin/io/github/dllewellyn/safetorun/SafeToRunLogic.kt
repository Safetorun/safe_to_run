package io.github.dllewellyn.safetorun

import io.github.dllewellyn.safetorun.reporting.SafeToRunReport

interface SafeToRunLogic {
    fun isSafeToRun(): SafeToRunReport
}
