package io.github.dllewellyn.safetorun.intents

import android.content.Intent
import io.github.dllewellyn.safetorun.intents.url.AllowUrlsBuilder
import io.github.dllewellyn.safetorun.intents.url.AllowUrlsBuilderImpl

/**
 * Class for building a verification builder use
 *
 * ```
 * Intent.verify()
 * ```
 */
class IntentVerificationBuilder internal constructor(
    private val intent: Intent
) : AllowUrlsBuilder by AllowUrlsBuilderImpl() {

    /**
     * Whether to allow an 'intent' inside this bundle
     */
    var allowContainingIntents: Boolean = false

    var actionOnSuccess: (() -> Unit)? = null
    var actionOnFailure: (() -> Unit)? = null

    internal fun verify() =
        (doesIntentCheckPass() && doesUrlCheckPass(gatherAllStrings(intent)))
            .also {
                if (it) {
                    actionOnSuccess?.invoke()
                } else {
                    actionOnFailure?.invoke()
                }
            }

    private fun doesIntentCheckPass() = if (!allowContainingIntents) {
        intent.extras?.gatherAllIntents()?.isEmpty() == true
    } else {
        true
    }

    private fun gatherAllStrings(intent: Intent) = intent.gatherAllStrings() ?: emptyList()
}

/**
 * Verify an intent based on the configuration
 *
 * Sample:
 * ```
 * verify {
 *  actionOnSuccess = { // Use intent }
 *  actionOnFailure = { // Log error }
 *  allowContainingIntents = true // Use me to permit intents being passed as an argument
 *  "mysafehost".allowHost() // Allow a specific
 *  allowAnyUrls = true // Not recommended, you should try to whitelist all URLs if possible to avoid opening
 *                      // malicious intents
 * }
 * ```
 * @param builderBlock the configuration to use
 */
fun Intent.verify(builderBlock: IntentVerificationBuilder.() -> Unit) = IntentVerificationBuilder(this).run {
    builderBlock()
    verify()
}
