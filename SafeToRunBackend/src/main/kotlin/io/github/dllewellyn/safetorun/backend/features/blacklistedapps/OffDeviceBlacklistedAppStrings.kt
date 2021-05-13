package io.github.dllewellyn.safetorun.backend.features.blacklistedapps

import io.github.dllewellyn.safetorun.features.blacklistedapps.BlacklistedAppStrings
import io.micronaut.context.annotation.Value
import javax.inject.Singleton

@Singleton
internal class OffDeviceBlacklistedAppStrings(
    @Value("\${safe.to.run.found_blacklisted_app_check}")
    private val foundBlacklistedAppMessage: String,
    @Value("\${safe.to.run.not_found_blacklisted_app_check}")
    private val didNotFindBlacklistedAppMessage: String
) : BlacklistedAppStrings {

    override fun foundBlacklistedAppMessage(blacklistedApp: String): String {
        return foundBlacklistedAppMessage.format(blacklistedApp)
    }

    override fun didNotFindBlacklistedAppMessage(): String {
        return didNotFindBlacklistedAppMessage
    }
}
