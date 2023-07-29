package offdevice.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.models.builders.deviceInformationBuilder
import com.safetorun.plus.offdevice.OffDeviceResultBuilder
import junit.framework.TestCase

internal class CompositeBuilderTest : TestCase() {

    fun `test that composite builder calls through to all compose builders`() {
        // Given
        val signature = "signatureexample"
        val deviceId = "deviceId"
        val compositeBuilder = com.safetorun.plus.offdevice.builders.CompositeBuilder(
            listOf(
                com.safetorun.plus.offdevice.OffDeviceResultBuilder {
                    it.signature(signature)
                    it
                },
                com.safetorun.plus.offdevice.OffDeviceResultBuilder {
                    it.deviceId(deviceId)
                    it
                }
            )
        )

        // When
        val result = compositeBuilder.buildOffDeviceResultBuilder(deviceInformationBuilder(""))

        // Then
        with(result.build()) {
            assertThat(signatureVerification.signatureVerificationString).isEqualTo(signature)
            assertThat(deviceId).isEqualTo(deviceId)
        }
    }
}
