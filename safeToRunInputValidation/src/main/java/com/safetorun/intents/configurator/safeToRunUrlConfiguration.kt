package com.safetorun.intents.configurator

import android.annotation.SuppressLint
import android.content.Context
import com.safetorun.intents.exeptions.ConfigurationNotFoundException
import com.safetorun.intents.url.UrlConfigVerifier
import com.safetorun.intents.url.urlVerification

@SuppressLint("StaticFieldLeak")
private object SafeToRunUrlConfigurator {

    lateinit var context: Context

    val configurationMap = mutableMapOf<String, UrlConfigVerifier>()

    fun initialise(context: Context) {
        this.context = context
    }

    fun registerIntentConfiguration(configurationName: String, verifier: UrlConfigVerifier) {
        configurationMap[configurationName] = verifier
    }

    fun verifyUrl(configurationName: String, url: String): Boolean =
        configurationMap[configurationName]?.let { url.urlVerification(it) }
            ?: throw ConfigurationNotFoundException.newConfigurationNotFoundException(
                configurationName
            )
}


internal fun initialiseSafeToRunUrlConfigurator(context: Context) =
    SafeToRunUrlConfigurator.initialise(context)

/**
 * Verify an intent against a particular configuration
 *
 * @param configurationName name of the configuration
 * @param url intent to verify
 */
fun verifyUrlStr(configurationName: String, url: String) =
    SafeToRunUrlConfigurator.verifyUrl(
        configurationName, url
    )

/**
 * Verify an intent against a configuration
 *
 * @param configurationName name of the configuration
 * @receiver the url to verify
 */
fun String.verifyIntent(configurationName: String) = verifyUrlStr(configurationName, this)

/**
 * Verify an intent against a configuration
 *
 * @param configurationName name of the configuration
 * @param actionOnFailure the action to execute if a failure occurs
 * @receiver the url to verify
 */
fun String.verifyIntent(configurationName: String, actionOnFailure: () -> Unit) =
    verifyUrlStr(configurationName, this, actionOnFailure)

/**
 * Verify an intent against a particular configuration
 *
 * @param configurationName name of the configuration
 * @param url url to verify
 * @param actionOnFailure the action to perform if the check fails
 */
fun verifyUrlStr(configurationName: String, url: String, actionOnFailure: () -> Unit) {
    if (SafeToRunUrlConfigurator.verifyUrl(configurationName, url).not()) {
        actionOnFailure()
    }
}

/**
 * Register a configuration for intent verification with a name and verifier
 *
 * @param configurationName name of the configuration
 * @param verifier verifier to assign to the configuration
 */
fun registerUrlVerification(configurationName: String, verifier: UrlConfigVerifier) =
    SafeToRunUrlConfigurator.registerIntentConfiguration(configurationName, verifier)
