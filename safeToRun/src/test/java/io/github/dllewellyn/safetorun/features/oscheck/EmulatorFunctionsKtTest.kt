package io.github.dllewellyn.safetorun.features.oscheck

import com.google.common.truth.Truth
import junit.framework.TestCase

internal class EmulatorFunctionsKtTest : TestCase() {
    fun `test emulator functions`() {
        Truth.assertThat(banAvdEmulator()).isNotNull()
        Truth.assertThat(banBluestacksEmulator()).isNotNull()
        Truth.assertThat(banGenymotionEmulator()).isNotNull()
    }
}
