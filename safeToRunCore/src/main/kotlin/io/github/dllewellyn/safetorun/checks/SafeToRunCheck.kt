package io.github.dllewellyn.safetorun.checks

import io.github.dllewellyn.safetorun.reporting.SafeToRunReport

/**
 * Defines a check that will run and return whether or not the device is
 * safe to run
 *
 */
interface SafeToRunCheck {

    /**
     * Runs the check to decide if it is safe to run
     *
     * @return a safe to run report
     */
    fun canRun(): SafeToRunReport
}
