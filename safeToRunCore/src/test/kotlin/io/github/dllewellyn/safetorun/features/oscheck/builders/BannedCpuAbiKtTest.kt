package io.github.dllewellyn.safetorun.features.oscheck.builders

import com.google.common.truth.Truth
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase

internal class BannedCpuAbiKtTest : TestCase() {
    private val osInformationQuery = mockk<OSInformationQuery>()
    private val bannedCpu = osInformationQuery.bannedCpus(BANNED_CPU)

    fun `test that when we have a banned cpu check with a banned cpu it fails`() {
        // Given
        every { osInformationQuery.cpuAbi() } returns listOf(NOT_BANNED_CPU)

        // When Then
        Truth.assertThat(bannedCpu().failed).isFalse()
    }

    fun `test that when we have a banned cpu check without a banned cpu it passed`() {
        // Given
        every { osInformationQuery.cpuAbi() } returns listOf(BANNED_CPU, NOT_BANNED_CPU)

        // When Then
        Truth.assertThat(bannedCpu().failed).isTrue()
    }

    companion object {
        const val BANNED_CPU = "banned"
        const val NOT_BANNED_CPU = "not_banned"
    }
}
