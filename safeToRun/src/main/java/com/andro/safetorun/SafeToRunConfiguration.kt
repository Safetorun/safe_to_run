package com.andro.safetorun

import com.andro.safetorun.checks.CompositeSafeToRunCheck
import com.andro.safetorun.checks.SafeToRunCheck

class SafeToRunConfiguration {

    private val safeToRunChecks = mutableListOf<SafeToRunCheck>()

    private fun add(safeToRunCheck: SafeToRunCheck) {
        safeToRunChecks.add(safeToRunCheck)
    }

    operator fun plus(safeToRunCheck: SafeToRunCheck) {
        add(safeToRunCheck)
    }

    operator fun plus(safeToRunCheck: () -> SafeToRunCheck) {
        add(safeToRunCheck())
    }

    fun build(): SafeToRunCheck = CompositeSafeToRunCheck(safeToRunChecks)
}

fun configure(
    configuration: SafeToRunConfiguration.() -> Unit
): SafeToRunConfiguration =
    SafeToRunConfiguration().apply {
        configuration()
    }
