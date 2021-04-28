package io.github.dllewellyn.safetorun.features.installorigin

import android.content.Context
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AndroidInstallOriginStringsTest : TestCase() {

    private val context = mockk<Context>()
    private val resources = SharedInstallOrigin.setupAMockResources()

    private val androidInstallOriginStrings = AndroidInstallOriginStrings(context)

    override fun setUp() {
        every { context.resources } returns resources
    }

    fun `test message if we cant find package`() {
        assertThat(androidInstallOriginStrings.couldNotFindPackage()).isEqualTo(SharedInstallOrigin.NOT_FOUND)
    }

    fun `test message if packageWasNotInAllowedList`() {
        assertThat(androidInstallOriginStrings.packageWasInAllowedList()).isEqualTo(SharedInstallOrigin.MATCHED)
    }

    fun `test message if packageWasInAllowedList`() {
        assertThat(androidInstallOriginStrings.packageWasNotInAllowedList("Abc")).isEqualTo(
            SharedInstallOrigin.NOT_MATCHING.format(
                "Abc"
            )
        )
    }
}