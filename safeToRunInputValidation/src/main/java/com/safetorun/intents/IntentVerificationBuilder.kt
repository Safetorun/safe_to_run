package com.safetorun.intents

import android.content.Context
import android.content.Intent
import com.safetorun.intents.file.DefaultFileUriMatcherBuilder
import com.safetorun.intents.file.FileUriMatcherBuilder
import com.safetorun.intents.url.AllowUrlsBuilder
import com.safetorun.intents.url.DefaultAllowUrlsBuilder

/**
 * Class for building a verification builder use
 *
 * ```
 * Intent.verify()
 * ```
 */
class IntentVerificationBuilder internal constructor(
    private val intent: Intent,
    private val context: Context
) : AllowUrlsBuilder by DefaultAllowUrlsBuilder(),
    FileUriMatcherBuilder by DefaultFileUriMatcherBuilder(context) {

    /**
     * Whether to allow an 'intent' inside this bundle
     */
    var allowContainingIntents: Boolean = false

    var actionOnSuccess: (() -> Unit)? = null
    var actionOnFailure: (() -> Unit)? = null

    internal fun verify() =
        (doesIntentCheckPass() && doesUrlCheckPass(gatherAllStrings(intent)))
            .also { passed ->
                if (passed) {
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

    private fun gatherAllStrings(intent: Intent) = intent.gatherAllStrings()
}

/**
 * Alias for intent verification builder
 */
typealias IntentVerifier = IntentVerificationBuilder.() -> Unit


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
 * @param context android context
 */
fun Intent.verify(context: Context, builderBlock: IntentVerifier) =
    IntentVerificationBuilder(this, context).run {
        builderBlock()
        verify()
    }
