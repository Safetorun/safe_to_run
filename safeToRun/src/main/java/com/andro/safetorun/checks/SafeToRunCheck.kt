package com.andro.safetorun.checks

import android.content.Context

interface SafeToRunCheck {
    fun canRun(context: Context): Boolean
}

class CompositeSafeToRunCheck(private val safeToRunChecks: List<SafeToRunCheck>) : SafeToRunCheck {

    override fun canRun(context: Context): Boolean {
        return safeToRunChecks.map {
            it.canRun(context)
        }.firstOrNull { it.not() } == null
    }

}