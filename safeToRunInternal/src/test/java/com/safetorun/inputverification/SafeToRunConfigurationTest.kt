package com.safetorun.inputverification

import com.google.common.truth.Truth.assertThat
import com.safetorun.resilienceshared.dto.CheckType
import com.safetorun.resilienceshared.dto.Severity
import com.safetorun.resilienceshared.dto.SingleCheck
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
                    singleCheck(Severity.Error) {
                        allChecks = listOf(check)
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
        val allowedHost = "safetorun.com"
        val fullUrl = "bbc.co.uk?abc=def"
        val config = ParameterConfig("Test", AllowedTypeCore.Any)
        val configDto = ParameterConfigDto("Test", AllowedTypeDto.Any)

        val str = safeToRun {
            inputVerification {
                urlConfiguration(CONFIGURATION_NAME) {
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
        assertThat(configuration.name).isEqualTo(CONFIGURATION_NAME)
        assertThat(configuration.configuration.allowAnyParameter).isTrue()
        assertThat(configuration.configuration.allowAnyUrl).isFalse()
        assertThat(configuration.configuration.allowedUrls.first()).isEqualTo(fullUrl)
        assertThat(configuration.configuration.allowedHost.first()).isEqualTo(allowedHost)
        assertThat(configuration.configuration.allowParameters.first()).isEqualTo(configDto)

    }

    @Test
    fun `test that input verification builder can build a configuration with allow all urls`() {
        val str = safeToRun {
            inputVerification {
                urlConfiguration(CONFIGURATION_NAME) {
                    allowAnyParameter = true
                    allowAnyUrl = false
                }
            }
        }

        val configuration =
            str.inputVerification?.urlConfigurations?.first() ?: throw IllegalAccessError()
        assertThat(configuration.name).isEqualTo(CONFIGURATION_NAME)
        assertThat(configuration.configuration.allowAnyParameter).isTrue()
        assertThat(configuration.configuration.allowAnyUrl).isFalse()

    }

    @Test
    fun `test that input verification builder can build a configuration with allow all files`() {
        val str = safeToRun {
            inputVerification {
                fileConfiguration(CONFIGURATION_NAME) {
                    allowAnyFile = true
                }
            }
        }

        val configuration =
            str.inputVerification?.fileConfiguration?.first() ?: throw IllegalAccessError()
        assertThat(configuration.name).isEqualTo(CONFIGURATION_NAME)
        assertThat(configuration.configuration.allowAnyFile).isTrue()

    }

    @Test
    fun `test that input verification builder can build a configuration with allow a specific file with string`() {
        val str = safeToRun {
            inputVerification {
                fileConfiguration(CONFIGURATION_NAME) {
                    TEST_FILE.allowFile()
                    +ABC_FILE
                }
            }
        }

        val configuration =
            str.inputVerification?.fileConfiguration?.first() ?: throw IllegalAccessError()
        assertThat(configuration.name).isEqualTo(CONFIGURATION_NAME)
        assertThat(configuration.configuration.allowAnyFile).isFalse()
        assertThat(configuration.configuration.allowedExactFiles).containsAtLeastElementsIn(
            listOf(TEST_FILE, ABC_FILE)
        )
    }

    @Test
    fun `test that input verification builder can build a configuration with allow a specific file`() {
        val str = safeToRun {
            inputVerification {
                fileConfiguration(CONFIGURATION_NAME) {
                    File(BLAH_DIR, "testfile.txt").allowFile()
                    +File(ABC_FILE)
                }
            }
        }

        val configuration =
            str.inputVerification?.fileConfiguration?.first() ?: throw IllegalAccessError()
        assertThat(configuration.name).isEqualTo(CONFIGURATION_NAME)
        assertThat(configuration.configuration.allowAnyFile).isFalse()
        assertThat(configuration.configuration.allowedExactFiles).containsAtLeastElementsIn(
            listOf(TEST_FILE, ABC_FILE)
        )
    }

    @Test
    fun `test that input verification builder can build a configuration with allow a directory`() {
        val str = safeToRun {
            inputVerification {
                fileConfiguration(CONFIGURATION_NAME) {
                    File(BLAH_DIR).allowDirectory { }
                    ABC_DIR.allowDirectory { allowSubdirectories = true }
                }
            }
        }

        val configuration =
            str.inputVerification?.fileConfiguration?.first() ?: throw IllegalAccessError()
        assertThat(configuration.name).isEqualTo(CONFIGURATION_NAME)
        assertThat(configuration.configuration.allowAnyFile).isFalse()
        assertThat(configuration.configuration.allowedParentDirectories).contains(
            ParentConfigurationDto(BLAH_DIR, false)
        )
        assertThat(configuration.configuration.allowedParentDirectories).contains(
            ParentConfigurationDto(ABC_DIR, true)
        )
    }

    companion object {
        const val CONFIGURATION_NAME = "test name"
        const val BLAH_DIR = "/data/data/blah"
        const val ABC_DIR = "/data/data/abc"
        const val TEST_FILE = "/data/data/blah/testfile.txt"
        const val ABC_FILE = "/data/data/blah/abc.txt"
    }

}
