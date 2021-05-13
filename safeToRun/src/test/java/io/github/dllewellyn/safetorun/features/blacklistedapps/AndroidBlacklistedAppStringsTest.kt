package io.github.dllewellyn.safetorun.features.blacklistedapps

import android.content.Context
import android.content.res.Resources
import io.github.dllewellyn.safetorun.R
import com.google.common.truth.Truth.assertThat
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class AndroidBlacklistedAppStringsTest : TestCase() {
    private val resources = mockk<Resources>()
    private val context = mockk<Context>()

    override fun setUp() {
        every { context.resources } returns resources
        every { resources.getString(R.string.no_blacklisted_apps_found) } returns NO_BLACKLISTED
        every { resources.getString(R.string.found_blacklisted_app, any()) } answers {
            String.format(BLACKLISTED, args[1].toStr().removePrefix("[").removeSuffix("]"))
        }
    }

    fun `test that blacklisted strings call through to context resources`() {
        // Given
        val blacklistedStrings = AndroidBlacklistedAppStrings(context)

        // When
        val result = blacklistedStrings.didNotFindBlacklistedAppMessage()

        // Then
        assertThat(result).isEqualTo(NO_BLACKLISTED)
    }

    fun `test that blacklisted strings call through to context resources when found`() {
        // Given
        val blacklistedStrings = AndroidBlacklistedAppStrings(context)

        // When
        val result = blacklistedStrings.foundBlacklistedAppMessage("abc")

        // Then
        assertThat(result).isEqualTo(String.format(BLACKLISTED, "abc"))
    }

    companion object {
        const val NO_BLACKLISTED = "no blacklisted"
        const val BLACKLISTED = "blacklisted %s"
    }
}
