package io.github.dllewellyn.safetorun.offdevice.builders

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDtoBuilder
import io.github.dllewellyn.safetorun.offdevice.OffDeviceResultBuilder
import junit.framework.TestCase

internal class CompositeBuilderTest : TestCase() {

    fun `test that composite builder calls through to all compose builders`() {
        // Given
        val signature = "signatureexample"
        val deviceId = "deviceId"
        val compositeBuilder = CompositeBuilder(
            listOf(
                OffDeviceResultBuilder {
                    it.signature(signature)
                    it
                },
                OffDeviceResultBuilder {
                    it.deviceId(deviceId)
                    it
                }
            )
        )

        // When
        val result = compositeBuilder.buildOffDeviceResultBuilder(DeviceInformationDtoBuilder(""))

        // Then
        with(result.buildPartial()) {
            assertThat(signatureVerification.signatureVerificationString).isEqualTo(signature)
            assertThat(deviceId).isEqualTo(deviceId)
        }
    }
}
