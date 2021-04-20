package com.andro.safetorun.checks

import com.andro.safetorun.reporting.SafeToRunReport

interface SafeToRunCheck {
    fun canRun(): SafeToRunReport
}