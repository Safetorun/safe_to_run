package com.andro.secure.util

import android.util.Log

fun Any.logVerbose(message: String) = Log.v(this::class.java.toString(), message)
