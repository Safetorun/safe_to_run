package com.safetorun.pinscreen.models

import java.util.Date

internal data class Attempts(val attempts: Int, val lastAttemptTime: Date = Date())
