package com.safetorun.inline

/**
 * Safe to run check (Return true if the check fails)
 */
typealias SafeToRunCheck = () -> Boolean


/**
 * Configure
 *
 * @param safeToRunChecks list of checks for warnings
 */
inline fun safeToRun(
    crossinline logger: (Boolean) -> Unit,
    vararg safeToRunChecks: SafeToRunCheck
): SafeToRunCheck = safeToRun(logger, safeToRunChecks.toList())


/**
 * Configure
 *
 * @param safeToRunChecks list of checks for warnings
 */
inline fun safeToRun(
    crossinline logger: (Boolean) -> Unit,
    safeToRunChecks: List<SafeToRunCheck>
): SafeToRunCheck = {
    safeToRun(safeToRunChecks).invoke()
        .also { logger(it) }
}

/**
 * Configure
 *
 * @param safeToRunChecks list of checks for warnings
 */
inline fun safeToRun(
    safeToRunChecks: List<SafeToRunCheck>
): SafeToRunCheck {
    return {
        safeToRunChecks
            .map { safeToRunCheck -> safeToRunCheck.invoke() }
            .any { failed -> failed }
    }
}

/**
 * Configure
 *
 * @param safeToRunChecks list of checks for warnings
 */
inline fun safeToRun(
    vararg safeToRunChecks: SafeToRunCheck
): SafeToRunCheck {
    return safeToRun(safeToRunChecks.toList())
}

/**
 * Build a safe to run check list
 * Example:
 * ```
 * safeToRun(buildSafeToRunCheckList {
 *     add {
 *       banAvdEmulatorCheck()
 *     }
 * }
 * ```
 *
 * @param builder a list builder. Add all checks you want to the list
 */
inline fun buildSafeToRunCheckList(builder: MutableList<SafeToRunCheck>.() -> Unit): List<SafeToRunCheck> =
    mutableListOf<SafeToRunCheck>().apply {
        builder()
    }
