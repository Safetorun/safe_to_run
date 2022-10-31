package com.safetorun.inputverification

import com.google.common.truth.Truth.assertThat
import com.safetorun.backendresilience.dto.CheckType
import com.safetorun.backendresilience.dto.Severity
import com.safetorun.backendresilience.dto.SingleCheck
import com.safetorun.inputverification.dto.AllowedTypeDto
import com.safetorun.inputverification.dto.ParameterConfigDto
import com.safetorun.inputverification.dto.ParentConfigurationDto
import com.safetorun.inputverification.model.AllowedTypeCore
import com.safetorun.inputverification.model.ParameterConfig
import com.safetorun.safeToRun
import org.junit.Test
import java.io.File


internal class SafeToRunConfigurationTest {

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

        val checks = str.backendResilience?.osCheckConfiguration?.first()?.configuration?.first()
        assertThat(checks?.allChecks?.first()).isEqualTo(check)
        assertThat(checks?.severity).isEqualTo(Severity.Error)

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

        assertThat(str.backendResilience?.installOriginCheck?.first()?.allowedInstallOrigins?.first())
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

        assertThat(str.backendResilience?.verifySignatureConfiguration?.first()?.allowedSignatures?.first())
            .isEqualTo(allowedSignature)
    }

    @Test
    fun `test that input verification builder can build a configuration with various URLs`() {
        val configurationName = "test name"
        val allowedHost = "safetorun.com"
        val fullUrl = "bbc.co.uk?abc=def"
        val config = ParameterConfig("Test", AllowedTypeCore.Any)
        val configDto = ParameterConfigDto("Test", AllowedTypeDto.Any)

        val str = safeToRun {
            inputVerification {
                urlConfiguration(configurationName) {
                    allowAnyParameter = true
                    allowAnyUrl = false
                    allowedHost.allowHost()
                    fullUrl.allowUrl()
                    config.allowParameter()
                }
            }
        }

        val configuration =
            str.inputVerification?.urlConfigurations?.first() ?: throw IllegalAccessError()
        assertThat(configuration.name).isEqualTo(configurationName)
        assertThat(configuration.configuration.allowAnyParameter).isTrue()
        assertThat(configuration.configuration.allowAnyUrl).isFalse()
        assertThat(configuration.configuration.allowedUrls.first()).isEqualTo(fullUrl)
        assertThat(configuration.configuration.allowedHost.first()).isEqualTo(allowedHost)
        assertThat(configuration.configuration.allowParameters.first()).isEqualTo(configDto)

    }

    @Test
    fun `test that input verification builder can build a configuration with allow all urls`() {
        val configurationName = "test name"
        val str = safeToRun {
            inputVerification {
                urlConfiguration(configurationName) {
                    allowAnyParameter = true
                    allowAnyUrl = false
                }
            }
        }

        val configuration =
            str.inputVerification?.urlConfigurations?.first() ?: throw IllegalAccessError()
        assertThat(configuration.name).isEqualTo(configurationName)
        assertThat(configuration.configuration.allowAnyParameter).isTrue()
        assertThat(configuration.configuration.allowAnyUrl).isFalse()

    }

    @Test
    fun `test that input verification builder can build a configuration with allow all files`() {
        val configurationName = "test name"
        val str = safeToRun {
            inputVerification {
                fileConfiguration(configurationName) {
                    allowAnyFile = true
                }
            }
        }

        val configuration =
            str.inputVerification?.fileConfiguration?.first() ?: throw IllegalAccessError()
        assertThat(configuration.name).isEqualTo(configurationName)
        assertThat(configuration.configuration.allowAnyFile).isTrue()

    }

    @Test
    fun `test that input verification builder can build a configuration with allow a specific file with string`() {
        val configurationName = "test name"
        val str = safeToRun {
            inputVerification {
                fileConfiguration(configurationName) {
                    "/data/data/blah/testfile.txt".allowFile()
                    +"/data/data/blah/abc.txt"
                }
            }
        }

        val configuration =
            str.inputVerification?.fileConfiguration?.first() ?: throw IllegalAccessError()
        assertThat(configuration.name).isEqualTo(configurationName)
        assertThat(configuration.configuration.allowAnyFile).isFalse()
        assertThat(configuration.configuration.allowedExactFiles).containsAtLeastElementsIn(
            listOf("/data/data/blah/testfile.txt", "/data/data/blah/abc.txt")
        )
    }

    @Test
    fun `test that input verification builder can build a configuration with allow a specific file`() {
        val configurationName = "test name"
        val str = safeToRun {
            inputVerification {
                fileConfiguration(configurationName) {
                    File("/data/data/blah", "testfile.txt").allowFile()
                    +File("/data/data/blah/abc.txt")
                }
            }
        }

        val configuration =
            str.inputVerification?.fileConfiguration?.first() ?: throw IllegalAccessError()
        assertThat(configuration.name).isEqualTo(configurationName)
        assertThat(configuration.configuration.allowAnyFile).isFalse()
        assertThat(configuration.configuration.allowedExactFiles).containsAtLeastElementsIn(
            listOf("/data/data/blah/testfile.txt", "/data/data/blah/abc.txt")
        )
    }

    @Test
    fun `test that input verification builder can build a configuration with allow a directory`() {
        val configurationName = "test name"
        val str = safeToRun {
            inputVerification {
                fileConfiguration(configurationName) {
                    File("/data/data/blah").allowDirectory { }
                }
            }
        }

        val configuration =
            str.inputVerification?.fileConfiguration?.first() ?: throw IllegalAccessError()
        assertThat(configuration.name).isEqualTo(configurationName)
        assertThat(configuration.configuration.allowAnyFile).isFalse()
        assertThat(configuration.configuration.allowedParentDirectories).contains(
            ParentConfigurationDto("/data/data/blah", false)
        )
    }

}
