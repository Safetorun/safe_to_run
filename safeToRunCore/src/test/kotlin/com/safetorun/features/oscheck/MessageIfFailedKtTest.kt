package com.safetorun.features.oscheck

import com.google.common.truth.Truth.assertThat
import com.safetorun.conditional.ConditionalResponse
import junit.framework.TestCase

internal class MessageIfFailedKtTest : TestCase() {

    fun `test that we get an error message if it fails`() {
        assertThat(ConditionalResponse(failed = true).messageIfFailed(FAIL_MESSAGE).message).isEqualTo(FAIL_MESSAGE)
    }

    fun `test that we get an no error message if it passes`() {
        assertThat(ConditionalResponse(failed = false).messageIfFailed(FAIL_MESSAGE).message).isNull()
    }

    companion object {
        const val FAIL_MESSAGE = "failure message"
    }
}
