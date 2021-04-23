package com.andro.safetorun

import com.andro.safetorun.checks.CompositeSafeToRunCheck
import com.andro.safetorun.checks.SafeToRunCheck

class SafeToRunConfiguration {

    private val safeToRunChecks = mutableListOf<SafeToRunCheck>()
    private val safeToRunWarnings = mutableListOf<SafeToRunCheck>()

    private fun add(safeToRunCheck: SafeToRunCheck) {
        safeToRunChecks.add(safeToRunCheck)
    }

    infix fun errorIf(safeToRunCheck: SafeToRunCheck) {
        add(safeToRunCheck)
    }

    infix fun errorIf(safeToRunCheck: () -> SafeToRunCheck) {
        add(safeToRunCheck())
    }

    infix fun warnIf(safeToRunCheck: SafeToRunCheck) {
        safeToRunWarnings.add(safeToRunCheck)
    }

    infix fun warnIf(safeToRunCheck: () -> SafeToRunCheck) {
        safeToRunWarnings.add(safeToRunCheck())
    }

    fun build(): SafeToRunCheck = CompositeSafeToRunCheck(safeToRunChecks, safeToRunWarnings)
}

fun configure(
    configuration: SafeToRunConfiguration.() -> Unit
): SafeToRunConfiguration =
    SafeToRunConfiguration().apply {
        configuration()
    }
