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

    val mp = mutableMapOf<String, IntentVerifier>()

    fun initialise(context: Context) {
        this.context = context
    }

    fun registerIntentConfiguration(configurationName: String, verifier: IntentVerifier) {
        mp[configurationName] = verifier
    }

    fun verifyIntent(configurationName: String, intent: Intent): Boolean =
        mp[configurationName]?.let { intent.verify(context, it) }
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
fun verifyIntent(configurationName: String, intent: Intent) =
    SafeToRunIntentConfigurator.verifyIntent(
        configurationName, intent
    )

/**
 * Register a configuration for intent verification with a name and verifier
 *
 * @param configurationName name of the configuration
 * @param verifier verifier to assign to the configuration
 */
fun registerIntentVerification(configurationName: String, verifier: IntentVerifier) =
    SafeToRunIntentConfigurator.registerIntentConfiguration(configurationName, verifier)
