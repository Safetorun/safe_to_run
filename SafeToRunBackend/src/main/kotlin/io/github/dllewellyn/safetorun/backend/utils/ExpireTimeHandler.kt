package io.github.dllewellyn.safetorun.backend.utils

import java.util.Date

interface ExpireTimeHandler {
    fun getExpiryTime(): Date
}
