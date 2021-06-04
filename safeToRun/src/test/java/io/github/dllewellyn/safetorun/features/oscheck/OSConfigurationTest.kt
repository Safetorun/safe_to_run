package io.github.dllewellyn.safetorun.features.oscheck

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase

internal class OSConfigurationTest : TestCase() {
    fun `test that calling os configuration test returns a valid result`() {
        assertThat(minOsVersion(-1)().failed).isFalse()
        assertThat(minOsVersion(1000)().failed).isTrue()
        assertThat(notManufacturer("")).isNotNull()
        assertThat(bannedBoard("")).isNotNull()
        assertThat(bannedBootloader("")).isNotNull()
        assertThat(bannedCpus("")).isNotNull()
        assertThat(bannedDevice("")).isNotNull()
        assertThat(bannedHardware("")).isNotNull()
        assertThat(bannedHost("")).isNotNull()
    }
}
