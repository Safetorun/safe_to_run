package com.safetorun.inline

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class SafeToRunKtTest {

    @Test
    fun `test that safe to run can log on success`() {
        safeToRun(
            logger = { assertThat(it).isTrue() },
            { true }
        )
    }

    @Test
    fun `test that safe to run can log on failure`() {
        safeToRun(
            logger = { assertThat(it).isFalse() },
            { false }
        )
    }
}
