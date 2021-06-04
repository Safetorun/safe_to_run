package io.github.dllewellyn.safetorun.features.oscheck

import com.google.common.truth.Truth
import io.github.dllewellyn.safetorun.features.oscheck.builders.bannedModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class BannedNotModelCheck : TestCase() {

    private val osInformationQuery = mockk<OSInformationQuery>()
    private val bannedModelCheck = osInformationQuery.bannedModel(BANNED_MODEL)

    fun `test that when we have a banned model check with a banned model it fails`() {
        // Given
        every { osInformationQuery.model() } returns NOT_BANNED_MODEL

        // When Then
        Truth.assertThat(bannedModelCheck().failed).isFalse()
    }

    fun `test that when we have a banned model check without a banned model it passed`() {
        // Given
        every { osInformationQuery.model() } returns BANNED_MODEL

        // When Then
        Truth.assertThat(bannedModelCheck().failed).isTrue()
    }

    companion object {
        const val BANNED_MODEL = "banned"
        const val NOT_BANNED_MODEL = "not_banned"
    }
}
