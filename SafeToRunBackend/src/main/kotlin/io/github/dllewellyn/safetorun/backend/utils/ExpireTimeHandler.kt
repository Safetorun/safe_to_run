package io.github.dllewellyn.safetorun.backend.utils

import java.util.Date

internal interface ExpireTimeHandler {
    fun getExpiryTime(): Date
}
