package com.safetorun.features.oscheck

typealias FailIf = () -> Boolean
typealias Unless = () -> Boolean

/**
 * Combine a safe to run check (to form another safe to run check)
 *
 * For example, if any 'fail if' is true, we'll fail UNLESS and 'Unless' is
 * false.
 *
 * @param failIfs fail if any of these are true
 * @param unless unless any of these checks are false
 *
 * @return fail or not
 */
inline fun safeToRunCombinedCheck(
    failIfs: List<FailIf> = emptyList(),
    unless: List<Unless> = emptyList()
) =
    doAnyUnlessesPass(unless).not() && failIfs
        .asSequence()
        .map { it() }
        .any { it }

/**
 * Do any unlesses pass?
 *
 * @param unless unlesses
 *
 * @return are there any unlesses that pass (e.g. return false)
 */
inline fun doAnyUnlessesPass(unless: List<Unless>) =
    unless.asSequence()
        .map { it() }
        .any { it.not() }
