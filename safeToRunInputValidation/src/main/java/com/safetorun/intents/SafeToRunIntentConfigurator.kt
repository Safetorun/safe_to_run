package com.safetorun.intents

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.safetorun.intents.exeptions.ConfigurationNotFoundException

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


fun initialiseSafeToRunIntentConfigurator(context: Context) =
    SafeToRunIntentConfigurator.initialise(context)

fun verifyIntent(configurationName: String, intent: Intent) =
    SafeToRunIntentConfigurator.verifyIntent(
        configurationName, intent
    )

fun registerIntentVerification(configurationName: String, verifier: IntentVerifier) =
    SafeToRunIntentConfigurator.registerIntentConfiguration(configurationName, verifier)
