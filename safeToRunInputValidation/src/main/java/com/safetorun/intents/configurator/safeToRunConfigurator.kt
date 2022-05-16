package com.safetorun.intents.configurator

import android.content.Context

/**
 * Initialise configuration. Do not use configured safe to run input verification
 * before this is called
 *
 * @param context app context
 */
fun initialiseSafeToRunConfigurator(context : Context) {
    initialiseSafeToRunIntentConfigurator(context)
}
