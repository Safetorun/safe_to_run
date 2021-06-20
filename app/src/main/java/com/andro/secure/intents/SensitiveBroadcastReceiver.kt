package com.andro.secure.intents

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.andro.secure.util.logVerbose

class SensitiveBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        logVerbose(intent.toString())
        logVerbose("Performing sensitive action!!!!!!!")
    }
}
