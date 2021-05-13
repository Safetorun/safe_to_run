package io.github.dllewellyn.safetorun.backend.features.installorigin

import com.google.common.truth.Truth.assertThat
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class OffDeviceInstallOriginStringsTest {

    @Inject
    lateinit var offDeviceInstallOriginStrings: OffDeviceInstallOriginStrings

    @Test
    fun `test that off device install strings are correct`() {
        assertThat(offDeviceInstallOriginStrings.couldNotFindPackage()).isNotEmpty()
        assertThat(offDeviceInstallOriginStrings.packageWasInAllowedList()).isNotEmpty()
        assertThat(offDeviceInstallOriginStrings.packageWasNotInAllowedList("abc")).contains("abc")
    }
}
