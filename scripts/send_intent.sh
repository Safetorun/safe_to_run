#!/bin/sh
adb shell am broadcast -a com.andro.secure.sensitive-action -n com.andro.secure/.intents.SensitiveBroadcastReceiver
