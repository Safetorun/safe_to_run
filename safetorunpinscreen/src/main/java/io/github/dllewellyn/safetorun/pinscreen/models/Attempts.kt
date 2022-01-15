package io.github.dllewellyn.safetorun.pinscreen.models

import java.util.Date

data class Attempts(val attempts: Int, val lastAttemptTime: Date = Date())
