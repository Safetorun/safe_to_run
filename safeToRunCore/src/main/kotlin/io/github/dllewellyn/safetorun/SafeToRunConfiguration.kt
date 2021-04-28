package io.github.dllewellyn.safetorun

import io.github.dllewellyn.safetorun.checks.CompositeSafeToRunCheck
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck

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

    fun SafeToRunCheck.warn() {
        safeToRunWarnings.add(this)
    }

    fun SafeToRunCheck.error() {
        safeToRunChecks.add(this)
    }

    infix fun warnIf(safeToRunCheck: SafeToRunCheck) {
        safeToRunWarnings.add(safeToRunCheck)
    }

    fun build(): SafeToRunCheck = CompositeSafeToRunCheck(safeToRunChecks, safeToRunWarnings)
}

fun configure(
    configuration: SafeToRunConfiguration.() -> Unit
): SafeToRunConfiguration =
    SafeToRunConfiguration().apply {
        configuration()
    }
