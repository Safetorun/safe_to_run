package io.github.dllewellyn.safetorun.inline

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
    safeToRunChecks: List<SafeToRunCheck>
): SafeToRunCheck {
    return {
        safeToRunChecks
            .map { safeToRunCheck -> safeToRunCheck.invoke() }
            .any { failed -> failed }
    }
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
fun buildSafeToRunCheckList(builder: MutableList<SafeToRunCheck>.() -> Unit): List<SafeToRunCheck> =
    mutableListOf<SafeToRunCheck>().apply {
        builder()
    }
