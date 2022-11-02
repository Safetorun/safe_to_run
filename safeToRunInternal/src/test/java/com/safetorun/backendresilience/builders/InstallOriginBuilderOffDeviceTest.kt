package com.safetorun.backendresilience.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.resilienceshared.dto.Severity
import org.junit.Test

internal class InstallOriginBuilderOffDeviceTest {

    @Test
    fun `test that install origin builder can build a configuration with install origins`() {
        val installOrigin = InstallOriginBuilderOffDevice(Severity.Warn)
            .apply {
                INSTALL_ORIGIN_1.allowInstallOrigin()
                +INSTALL_ORIGIN_2
            }
            .build()

        assertThat(installOrigin.allowedInstallOrigins).containsAtLeast(
            INSTALL_ORIGIN_1,
            INSTALL_ORIGIN_2
        )
        assertThat(installOrigin.severity).isEqualTo(Severity.Warn)
    }

    @Test
    fun `test that install origin builder can build a configuration without install originss`() {
        val installOrigin = InstallOriginBuilderOffDevice(Severity.Error)
            .build()

        assertThat(installOrigin.severity).isEqualTo(Severity.Error)
    }

    companion object {
        const val INSTALL_ORIGIN_1 = "com.install.origin.other"
        const val INSTALL_ORIGIN_2 = "com.install.origin"
    }
}
