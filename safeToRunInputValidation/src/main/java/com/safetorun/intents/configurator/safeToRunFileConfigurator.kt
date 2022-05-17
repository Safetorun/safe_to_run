package com.safetorun.intents.configurator

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import com.safetorun.intents.exeptions.ConfigurationNotFoundException
import com.safetorun.intents.file.FileVerifier
import com.safetorun.intents.file.verifyFile
import java.io.File

@SuppressLint("StaticFieldLeak")
private object SafeToRunFileConfigurator {

    lateinit var context: Context

    val configurationMap = mutableMapOf<String, FileVerifier>()

    fun initialise(context: Context) {
        this.context = context
    }

    fun registerFileConfiguration(configurationName: String, verifier: FileVerifier) {
        configurationMap[configurationName] = verifier
    }

    fun verifyFile(configurationName: String, file: File): Boolean =
        configurationMap[configurationName]?.let { file.verifyFile(context, it) }
            ?: throw ConfigurationNotFoundException.newConfigurationNotFoundException(
                configurationName
            )

    fun verifyFile(configurationName: String, uri: Uri): Boolean =
        configurationMap[configurationName]?.let { uri.verifyFile(context, it) }
            ?: throw ConfigurationNotFoundException.newConfigurationNotFoundException(
                configurationName
            )
}


internal fun initialiseSafeToRunFileConfigurator(context: Context) =
    SafeToRunFileConfigurator.initialise(context)

/**
 * Verify an file against a particular configuration
 *
 * @param configurationName name of the configuration
 * @param file file to verify
 */
fun verifyFile(configurationName: String, file: File) =
    SafeToRunFileConfigurator.verifyFile(
        configurationName, file
    )

/**
 * Verify an file against a particular configuration
 *
 * @param configurationName name of the configuration
 * @param uri file uri to verify
 */
fun verifyFileUri(configurationName: String, uri: Uri) =
    SafeToRunFileConfigurator.verifyFile(
        configurationName, uri
    )

/**
 * Verify an file against a particular configuration
 *
 * @param configurationName name of the configuration
 * @param uri file uri to verify
 * @param actionOnFailure action to execute if the pass fails
 */
fun verifyFileUri(configurationName: String, uri: Uri, actionOnFailure: () -> Unit) {
    if (
        SafeToRunFileConfigurator.verifyFile(
            configurationName, uri
        ).not()
    ) {
        actionOnFailure()
    }
}

/**
 * Verify an file against a particular configuration
 *
 * @param configurationName name of the configuration
 * @param file file to verify
 * @param actionOnFailure the action to perform if the check fails
 */
fun verifyFile(configurationName: String, file: File, actionOnFailure: () -> Unit) {
    if (SafeToRunFileConfigurator.verifyFile(
            configurationName, file
        ).not()
    ) {
        actionOnFailure()
    }
}

/**
 * Register a configuration for file verification with a name and verifier
 *
 * @param configurationName name of the configuration
 * @param verifier verifier to assign to the configuration
 */
fun registerFileVerification(configurationName: String, verifier: FileVerifier) =
    SafeToRunFileConfigurator.registerFileConfiguration(configurationName, verifier)

/**
 * Verify an file against a particular configuration
 *
 * @param configurationName name of the configuration
 * @param actionOnFailure the action to perform if the check fails
 * @receiver file to verify
 */
fun File.verifyFile(configurationName: String, actionOnFailure: () -> Unit) =
    verifyFile(configurationName, this, actionOnFailure)

/**
 * Verify an file against a particular configuration
 *
 * @param configurationName name of the configuration
 * @receiver file to verify
 */
fun File.verifyFile(configurationName: String) =
    verifyFile(configurationName, this)

/**
 * Verify an file against a particular configuration
 *
 * @param configurationName name of the configuration
 * @receiver uri to verify
 */
fun Uri.verifyFile(configurationName: String) = verifyFileUri(configurationName, this)

/**
 * Verify an file against a particular configuration
 *
 * @param configurationName name of the configuration
 * @param actionOnFailure the action to perform if the check fails
 * @receiver uri to verify
 */
fun Uri.verifyFile(configurationName: String, actionOnFailure: () -> Unit) =
    verifyFileUri(configurationName, this, actionOnFailure)
