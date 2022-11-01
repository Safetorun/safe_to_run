package com.safetorun.inputverification

import com.google.common.truth.Truth
import com.safetorun.backendresilience.dto.BlacklistedAppConfiguration
import com.safetorun.backendresilience.dto.CheckType
import com.safetorun.backendresilience.dto.Severity
import com.safetorun.backendresilience.dto.SingleCheck
import com.safetorun.safeToRun
import org.junit.Test

internal class BlacklistedAppValidationTests {

    @Test
    fun `test that blacklisted app builder can build a configuration with a blacklisted app`() {

        val packageToBlacklist1 = "com.blacklisted.app"
        val packageToBlacklist2 = "com.other.blacklisted.app"

        val str = safeToRun {
            backendResilience {
                blacklistedApp(Severity.Error) {
                    +packageToBlacklist1
                    packageToBlacklist2.blacklistApp()
                }
            }
        }

        val checks = str.backendResilience?.blacklistedAppCheck
        Truth.assertThat(checks).contains(
            BlacklistedAppConfiguration(
                blacklistedApps = listOf(packageToBlacklist1, packageToBlacklist2),
                severity = Severity.Error
            )
        )

    }

    @Test
    fun `test that blacklisted app builder can build a configuration with an os check`() {

        val singleCheckStrVal = "abc"
        val checkUuid = "uuid"
        val confName = "Test name"

        val check = SingleCheck(
            stringValue = singleCheckStrVal,
            checkType = CheckType.MinOsCheck,
            checkUuid = checkUuid
        )

        val str = safeToRun {
            backendResilience {
                oSCheck {
                    add {
                        allChecks = listOf(check)
                        severity = Severity.Error
                        osConfigurationName = confName
                    }
                }
            }
        }

        val checks =
            str.backendResilience?.osCheckConfiguration?.first()?.configuration?.first()
        Truth.assertThat(checks?.allChecks?.first()).isEqualTo(check)
        Truth.assertThat(checks?.severity).isEqualTo(Severity.Error)

    }

    @Test
    fun `test that blacklisted app builder can build a configuration with an allowed install origin`() {
        val allowedOrigin = "com.android.vending"

        val str = safeToRun {
            backendResilience {
                installOriginCheck(Severity.Error) {
                    allowedOrigin.allowInstallOrigin()
                }
            }
        }

        Truth.assertThat(str.backendResilience?.installOriginCheck?.first()?.allowedInstallOrigins?.first())
            .isEqualTo(allowedOrigin)
    }

    @Test
    fun `test that blacklisted app builder can build a configuration with an allowed signature`() {
        val allowedSignature = "testsignature"

        val str = safeToRun {
            backendResilience {
                verifySignature(Severity.Error) {
                    allowedSignature.allowSignature()
                }
            }
        }

        Truth.assertThat(str.backendResilience?.verifySignatureConfiguration?.first()?.allowedSignatures?.first())
            .isEqualTo(allowedSignature)
    }
}
