package com.andro.safetorun.checks

import android.content.Context
import com.andro.safetorun.reporting.SafeToRunReport

interface SafeToRunCheck {
    fun canRun(context: Context): SafeToRunReport
}