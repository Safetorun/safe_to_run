package com.safetorun.backendresilience

import com.google.common.truth.Truth.assertThat
import com.safetorun.resilienceshared.OnDeviceResilienceBuilder
import com.safetorun.resilienceshared.dto.BlacklistedAppConfiguration
import com.safetorun.resilienceshared.dto.CheckComparator
import com.safetorun.resilienceshared.dto.IntCheckType
import org.junit.Test

internal class BackendResilienceBuilderTest {

    @Test
    fun `test that we can add a basic configuration`() {
        val resilience = OnDeviceResilienceBuilder()
            .apply {
                blacklistedApp {
                    BLACKLISTED_APP.blacklistApp()
                }

                oSCheck {
                    singleCheck {
                        IntCheckType.MinOsCheck.failIf(30, CheckComparator.GREATER_THAN)
                    }
                }

                verifySignature {
                    ACCEPTED_SIGNATURE.allowSignature()
                }

                installOriginCheck {
                    INSTALL_ORIGIN.allowInstallOrigin()
                }
            }
            .build()

        // Install origin
        assertThat(resilience.installOriginCheck).contains(
            INSTALL_ORIGIN
        )

        // Signature check
        assertThat(resilience.allowedSignatures).contains(
            ACCEPTED_SIGNATURE
        )

        // OS Check
        val singleCheck =
            resilience.osCheckConfiguration.first().allIntChecks.first()
        assertThat(singleCheck.checkType).isEqualTo(IntCheckType.MinOsCheck)
        assertThat(singleCheck.comparator).isEqualTo(CheckComparator.GREATER_THAN)
        assertThat(singleCheck.intValue).isEqualTo(30)

        // Assert blacklisted app is in the list
        assertThat(resilience.blacklistedApps).contains(BLACKLISTED_APP)
    }

    companion object {
        const val BLACKLISTED_APP = "com.blacklisted.app"
        const val ACCEPTED_SIGNATURE = "SIGNATURE"
        const val INSTALL_ORIGIN = "com.play.store"
    }
}
