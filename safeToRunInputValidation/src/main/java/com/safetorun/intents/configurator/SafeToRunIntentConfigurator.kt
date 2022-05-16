package com.safetorun.intents.configurator

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.safetorun.intents.IntentVerifier
import com.safetorun.intents.exeptions.ConfigurationNotFoundException
import com.safetorun.intents.verify

@SuppressLint("StaticFieldLeak")
private object SafeToRunIntentConfigurator {

    lateinit var context: Context

    val configurationMap = mutableMapOf<String, IntentVerifier>()

    fun initialise(context: Context) {
        this.context = context
    }

    fun registerIntentConfiguration(configurationName: String, verifier: IntentVerifier) {
        configurationMap[configurationName] = verifier
    }

    fun verifyIntent(configurationName: String, intent: Intent): Boolean =
        configurationMap[configurationName]?.let { intent.verify(context, it) }
            ?: throw ConfigurationNotFoundException.newConfigurationNotFoundException(
                configurationName
            )
}


internal fun initialiseSafeToRunIntentConfigurator(context: Context) =
    SafeToRunIntentConfigurator.initialise(context)

/**
 * Verify an intent against a particular configuration
 *
 * @param configurationName name of the configuration
 * @param intent intent to verify
 */
fun verifyFile(configurationName: String, intent: Intent) =
    SafeToRunIntentConfigurator.verifyIntent(
        configurationName, intent
    )

/**
 * Verify an intent against a configuration
 *
 * @param configurationName name of the configuration
 * @receiver the intent to verify
 */
fun Intent.verifyUrl(configurationName: String) = verifyFile(configurationName, this)

/**
 * Verify an intent against a configuration
 *
 * @param configurationName name of the configuration
 * @param actionOnFailure the action to execute if a failure occurs
 * @receiver the intent to verify
 */
fun Intent.verifyUrl(configurationName: String, actionOnFailure: () -> Unit) =
    verifyFile(configurationName, this, actionOnFailure)

/**
 * Verify an intent against a particular configuration
 *
 * @param configurationName name of the configuration
 * @param intent intent to verify
 * @param actionOnFailure the action to perform if the check fails
 */
fun verifyFile(configurationName: String, intent: Intent, actionOnFailure: () -> Unit) {
    if (SafeToRunIntentConfigurator.verifyIntent(
            configurationName, intent
        ).not()
    ) {
        actionOnFailure()
    }
}

/**
 * Register a configuration for intent verification with a name and verifier
 *
 * @param configurationName name of the configuration
 * @param verifier verifier to assign to the configuration
 */
fun registerIntentVerification(configurationName: String, verifier: IntentVerifier) =
    SafeToRunIntentConfigurator.registerIntentConfiguration(configurationName, verifier)
