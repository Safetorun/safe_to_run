package io.github.dllewellyn.safetorun.backend.features.oscheck

import com.google.common.truth.Truth.assertThat
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class OffDeviceOSDetectionStringsTest {

    @Inject
    lateinit var deviceStrings: OffDeviceOSDetectionStrings

    @Test
    fun `test that we can off device strings`() {
        assertThat(deviceStrings.genericFailureMessage()).isNotEmpty()
        assertThat(deviceStrings.genericPassMessage()).isNotEmpty()
    }
}
