package io.github.dllewellyn.safetorun.backend.builder

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.models.models.deviceInformation
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class DefaultSafeToRunAbstractFactoryTest {

    @Inject
    private lateinit var factory: DefaultSafeToRunAbstractFactory

    private val deviceInformation = deviceInformation("") {
        installOrigin("install")
        installedApplication("packagename")
        osVersion("osversion")
        manufacturer("manufacturer")
        signature("signature")
    }

    @Test
    fun `test that we can generate safe to run from factory `() {
        assertThat(factory.generateSafeToRun(deviceInformation)).isNotNull()
    }
}
