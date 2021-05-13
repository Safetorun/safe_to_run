package io.github.dllewellyn.safetorun.backend.utils

import org.joda.time.DateTime
import org.joda.time.Hours
import java.util.Date
import javax.inject.Singleton

@Singleton
class DefaultExpireTimeHandler : ExpireTimeHandler {
    override fun getExpiryTime(): Date {
        return DateTime().withPeriodAdded(Hours.ONE, 1)
            .toDate()
    }
}
