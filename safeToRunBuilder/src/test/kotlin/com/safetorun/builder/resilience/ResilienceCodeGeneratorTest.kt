package com.safetorun.builder.resilience

import com.google.common.truth.Truth.assertThat
import com.safetorun.SafeToRunConfiguration
import com.safetorun.resilienceshared.dto.CheckComparator
import com.safetorun.resilienceshared.dto.IntCheckType
import com.safetorun.resilienceshared.dto.StringCheckType
import com.safetorun.safeToRun
import org.junit.Test

internal class ResilienceCodeGeneratorTest {

    @Test
    fun `test resilience builder can handle debug check`() {
        val config = safeToRun {
            onDeviceResilience {
                banDebugger()
            }
        }

        val onDevice = buildConfigToString(config)

        assertThat(onDevice).isEqualTo(
            COMMON.format(
                """
   {
    com.safetorun.features.debug.banDebugCheck()
  }
  ,"""
            ).trimIndent()
        )

    }

    @Test
    fun `test resilience builder can install origin check`() {
        val config = safeToRun {
            onDeviceResilience {
                installOriginCheck {
                    "com.install.origin".allowInstallOrigin()
                    "com.install.origin2".allowInstallOrigin()
                }
            }
        }

        val onDevice = buildConfigToString(config)
        val installOriginCheck =
            "com.safetorun.features.installorigin.installOriginCheckWithoutDefaultsCheck"
        assertThat(onDevice).isEqualTo(
            COMMON.format(
                """
   {
    $installOriginCheck("com.install.origin","com.install.origin2")
  }
  ,"""
            ).trimIndent()
        )

    }

    @Test
    fun `test os check check works`() {
        val config = safeToRun {
            onDeviceResilience {
                oSCheck {
                    singleCheck {
                        StringCheckType.BannedHardware.failIf("Abc", CheckComparator.EQUALS)
                        StringCheckType.BannedBoard.unless("Def", CheckComparator.EQUALS)
                    }
                }
            }
        }

        val onDevice = buildConfigToString(config)

        assertThat(onDevice).isEqualTo(
            COMMON.format(
                """
   {
    com.safetorun.features.oscheck.safeToRunCombinedCheck(
    listOf(
    { com.safetorun.features.oscheck.bannedHardwareCheck("Abc") },
    ),
    listOf(
    { com.safetorun.features.oscheck.bannedBoardCheck("Def") },
    )
    )
  }
  ,"""
            ).trimIndent()
        )
    }

    @Test
    fun `test os check banned works`() {
        val config = safeToRun {
            onDeviceResilience {
                oSCheck {
                    singleCheck {
                        StringCheckType.BannedCpuAbi.failIf("Abc", CheckComparator.EQUALS)
                        StringCheckType.BannedDevice.unless("Def", CheckComparator.EQUALS)
                    }
                }
            }
        }

        val onDevice = buildConfigToString(config)

        assertThat(onDevice).isEqualTo(
            COMMON.format(
                """
   {
    com.safetorun.features.oscheck.safeToRunCombinedCheck(
    listOf(
    { com.safetorun.features.oscheck.bannedCpusCheck("Abc") },
    ),
    listOf(
    { com.safetorun.features.oscheck.bannedDeviceCheck("Def") },
    )
    )
  }
  ,"""
            ).trimIndent()
        )
    }

    @Test
    fun `test os check check works with os min version`() {
        val config = safeToRun {
            onDeviceResilience {
                oSCheck {
                    singleCheck {
                        IntCheckType.MinOsCheck.failIf(31, CheckComparator.GREATER_THAN)
                        IntCheckType.MinOsCheck.unless(30, CheckComparator.GREATER_THAN)
                    }
                }
            }
        }

        val onDevice = buildConfigToString(config)

        assertThat(onDevice).isEqualTo(
            COMMON.format(
                """
   {
    com.safetorun.features.oscheck.safeToRunCombinedCheck(
    listOf(
    { com.safetorun.features.oscheck.minOsVersionCheck(31) },
    ),
    listOf(
    { com.safetorun.features.oscheck.minOsVersionCheck(30) },
    )
    )
  }
  ,"""
            ).trimIndent()
        )
    }

    @Test
    fun `test root detection check works`() {
        val config = safeToRun {
            onDeviceResilience {
                rootCheck {
                    tolerateBusyBox = true
                }
            }
        }

        val onDevice = buildConfigToString(config)

        assertThat(onDevice).isEqualTo(
            COMMON.format(
                """
   {
    com.safetorun.features.rootdetection.rootDetectionCheck(true)
  }
  ,"""
            ).trimIndent()
        )

    }

    @Test
    fun `test resilience builder can blacklist apps`() {
        val config = safeToRun {
            onDeviceResilience {
                blacklistedApp {
                    "abc".blacklistApp()
                    "def".blacklistApp()
                }
            }
        }

        val onDevice = buildConfigToString(config)

        assertThat(onDevice).isEqualTo(
            COMMON.format(
                """
   {
    com.safetorun.features.blacklistedapps.blacklistedAppCheck("abc","def")
  }
  ,"""
            ).trimIndent()
        )

    }

    @Test
    fun `test resilience builder can verify signature`() {
        val config = safeToRun {
            onDeviceResilience {
                verifySignature {
                    "abc".allowSignature()
                    "def".allowSignature()
                }
            }
        }

        val onDevice = buildConfigToString(config)

        assertThat(onDevice).isEqualTo(
            COMMON.format(
                """
   {
    com.safetorun.features.signatureverify.verifySignatureCheck("abc","def")
  }
  ,"""
            ).trimIndent()
        )

    }

    private fun buildConfigToString(config: SafeToRunConfiguration): String {
        return ResilienceCodeGenerator(config.ondeviceResilience!!).build()
            .toString().trimIndent()
    }

    companion object {
        const val COMMON = """
public inline fun android.content.Context.verifyDevice(): kotlin.Boolean {
  val checks = com.safetorun.`inline`.safeToRun(%s
  )
  return checks()
}
        """
    }
}
