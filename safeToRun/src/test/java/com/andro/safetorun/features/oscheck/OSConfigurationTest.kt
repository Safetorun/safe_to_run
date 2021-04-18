package com.andro.safetorun.features.oscheck

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase

internal class OSConfigurationTest : TestCase() {


    fun `test that calling os configuration test returns a valid result`() {
        assertThat(OSConfiguration.minOsVersion(-1)().failed).isFalse()
        assertThat(OSConfiguration.minOsVersion(1000)().failed).isTrue()
        assertThat(OSConfiguration.notManufacturer("")).isNotNull()
    }
}