package com.safetorun.features.oscheck.builders

import com.google.common.truth.Truth
import com.safetorun.features.oscheck.OSInformationQuery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class BannedBoardKtTest : TestCase() {

    private val osInformationQuery = mockk<OSInformationQuery>()

    fun `test that when we have a banned board check with a banned board it fails`() {
        // Given
        every { osInformationQuery.board() } returns NOT_BANNED_BOARDS

        // When Then
        Truth.assertThat(osInformationQuery.bannedBoard(BANNED_BOARDS)().failed).isFalse()
    }

    fun `test that when we have a banned board check without a banned board it passed`() {
        // Given
        every { osInformationQuery.board() } returns BANNED_BOARDS

        // When Then
        Truth.assertThat(osInformationQuery.bannedBoard(BANNED_BOARDS)().failed).isTrue()
    }

    companion object {
        const val BANNED_BOARDS = "banned"
        const val NOT_BANNED_BOARDS = "not_banned_boards"
    }
}
