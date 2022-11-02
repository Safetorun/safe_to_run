package com.safetorun.resilienceshared.builders

import com.google.common.truth.Truth
import org.junit.Test

internal class BaseInstallOriginBuilderTest {
    @Test
    fun `test that install origin builder can build a configuration with install origins`() {
        val installOrigin = BaseInstallOriginBuilder()
            .apply {
                INSTALL_ORIGIN_1.allowInstallOrigin()
                +INSTALL_ORIGIN_2
            }
            .build()

        Truth.assertThat(installOrigin.allowedInstallOrigins).containsAtLeast(
            INSTALL_ORIGIN_1,
            INSTALL_ORIGIN_2
        )
    }

    @Test
    fun `test that install origin builder can build a configuration without install originss`() {
        val installOrigin = BaseInstallOriginBuilder()
            .build()

        Truth.assertThat(installOrigin.allowedInstallOrigins).isEmpty()
    }

    companion object {
        const val INSTALL_ORIGIN_1 = "com.install.origin.other"
        const val INSTALL_ORIGIN_2 = "com.install.origin"
    }
}
