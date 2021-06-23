package io.github.dllewellyn.safetorun.intents

import android.content.Intent

class IntentVerificationBuilder internal constructor(private val intent: Intent) {

    var allowContainingIntents: Boolean = false
    var allowAnyUrls: Boolean = false

    private val urlRegex by lazy {
        "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]".toRegex()
    }

    private var actionOnSuccess: (() -> Unit)? = null
    private var actionOnFailure: (() -> Unit)? = null

    internal fun verify() = if (!allowContainingIntents) {
        intent.extras?.gatherAllIntents()?.isEmpty() == true
                && urls()
    } else {
        true
    }.also {
        if (it) {
            actionOnSuccess?.invoke()
        } else {
            actionOnFailure?.invoke()
        }
    }

    private fun urls() = if (!allowAnyUrls) {
        intent.extras?.gatherAllStrings()?.any {
            urlRegex.matches(it)
        }?.not() ?: true
    } else {
        true
    }
}

fun Intent.verify(builderBlock: IntentVerificationBuilder.() -> Unit) = IntentVerificationBuilder(this).run {
    builderBlock()
    verify()
}
