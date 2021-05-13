package io.github.dllewellyn.safetorun.backend.features.blacklistedapps

import com.google.common.truth.Truth.assertThat
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject


@MicronautTest
internal class OffDeviceBlacklistedAppStringsTest {

    @Inject
    private lateinit var offDeviceStrings: OffDeviceBlacklistedAppStrings

    @Test
    fun `test that strings are set when passed in`() {
        assertThat(offDeviceStrings.didNotFindBlacklistedAppMessage()).isNotEmpty()
        assertThat(offDeviceStrings.foundBlacklistedAppMessage("abc")).contains("abc")
    }
}
