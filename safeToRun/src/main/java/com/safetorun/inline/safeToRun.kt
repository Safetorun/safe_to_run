package com.safetorun.inline

/**
 * Safe to run check (Return true if the check fails)
 */
typealias SafeToRunCheck = () -> Boolean
//
///**
// * Initialise safe to run plus
// *
// * @param apiKey api key for the backend
// */
//fun initialiseSafeToRunPlus(apiKey: String) {
//    com.safetorun.plus.initialiseSafeToRunPlus(apiKey)
//}
//
///**
// * Safe to run logger for verify.
// *
// * Note: Be sure you call [initialiseSafeToRunPlus] before using this method
// */
//fun <T> Context.verifyLogger(
//    checkName: String
//): (Boolean, T?) -> Unit = { value, extraData ->
//    verify<T>(checkName).invoke(value, extraData)
//}
//
///**
// * Safe to run logger for verify.
// *
// * Note: Be sure you call [initialiseSafeToRunPlus] before using this method
// */
//fun Context.logger(checkName: String): (Boolean) -> Unit = {
//    plusLogger(checkName).invoke(it)
//}
//
///**
// * Safe to run logger for checker
// */
//@Deprecated(
//    "Use initialiseSafeToRunPlus and logger instead",
//    ReplaceWith(
//        "initialiseSafeToRunPlus(apiKey); logger(checkName)",
//        imports = ["com.safetorun.plus.initialiseSafeToRunPlus", "com.safetorun.plus.logger"]
//    )
//)
//fun Context.logger(apiKey: String, checkName: String): (Boolean) -> Unit = {
//    initialiseSafeToRunPlus(apiKey)
//    plusLogger(checkName).invoke(it)
//}

/**
 * Configure
 *
 * @param safeToRunChecks list of checks for warnings
 */
inline fun safeToRunWithLogger(
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
