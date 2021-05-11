package io.github.dllewellyn.safetorun.backend.builder

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.SafeToRunSingle
import io.github.dllewellyn.safetorun.backend.models.deviceInformation
import org.junit.jupiter.api.Test

internal class DefaultSafeToRunAbstractFactoryTest {

    private val factory = DefaultSafeToRunAbstractFactory(SafeToRunSingle)

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