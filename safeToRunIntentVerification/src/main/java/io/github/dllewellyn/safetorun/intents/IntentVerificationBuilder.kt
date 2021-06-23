package io.github.dllewellyn.safetorun.intents

import android.content.Intent

/**
 * Class for building a verification builder use
 *
 * ```
 * Intent.verify()
 * ```
 */
class IntentVerificationBuilder internal constructor(private val intent: Intent) {

    /**
     * Whether to allow an 'intent' inside this bundle
     */
    var allowContainingIntents: Boolean = false

    /**
     * Whether to allow any urls inside the bundle
     */
    var allowAnyUrls: Boolean = false

    private val urlRegex by lazy {
        "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]".toRegex()
    }

    private var actionOnSuccess: (() -> Unit)? = null
    private var actionOnFailure: (() -> Unit)? = null

    internal fun verify() =
        (handleContainingIntents() && handleUrlStrings())
            .also {
                if (it) {
                    actionOnSuccess?.invoke()
                } else {
                    actionOnFailure?.invoke()
                }
            }

    private fun handleContainingIntents() = if (!allowContainingIntents) {
        intent.extras?.gatherAllIntents()?.isEmpty() == true
    } else {
        true
    }

    private fun handleUrlStrings() = if (!allowAnyUrls) {
        intent.extras?.gatherAllStrings()?.any {
            urlRegex.matches(it)
        }?.not() ?: true
    } else {
        true
    }
}

/**
 * Verify an intent based on the configuration
 *
 * @param builderBlock the configuration to use
 */
fun Intent.verify(builderBlock: IntentVerificationBuilder.() -> Unit) = IntentVerificationBuilder(this).run {
    builderBlock()
    verify()
}
