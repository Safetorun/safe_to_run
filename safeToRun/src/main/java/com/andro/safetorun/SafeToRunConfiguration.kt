package com.andro.safetorun

import android.content.Context
import com.andro.safetorun.checks.CompositeSafeToRunCheck
import com.andro.safetorun.checks.SafeToRunCheck

class SafeToRunConfiguration(val context: Context) {

    private val safeToRunChecks = mutableListOf<SafeToRunCheck>()

    fun add(safeToRunCheck: SafeToRunCheck) {
        safeToRunChecks.add(safeToRunCheck)
    }

    operator fun plus(safeToRunCheck: SafeToRunCheck) {
        add(safeToRunCheck)
    }

    fun build(): SafeToRunCheck = CompositeSafeToRunCheck(safeToRunChecks)
}

fun configure(
    context: Context,
    configuration: SafeToRunConfiguration.() -> Unit
): SafeToRunConfiguration =
    SafeToRunConfiguration(context).apply {
        configuration()
    }
