package com.safetorun.inline

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class SafeToRunKtTest {

    @Test
    fun `test that safe to run can log on success`() {
        safeToRunWithLogger(
            logger = { assertThat(it).isTrue() },
            { true }
        )
    }

    @Test
    fun `test that safe to run can log on failure`() {
        safeToRunWithLogger(
            logger = { assertThat(it).isFalse() },
            { false }
        )
    }
}
