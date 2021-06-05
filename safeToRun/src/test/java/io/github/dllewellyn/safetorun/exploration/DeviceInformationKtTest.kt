package io.github.dllewellyn.safetorun.exploration

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.api.deviceInformation
import junit.framework.TestCase

internal class DeviceInformationKtTest : TestCase() {

    fun `test that device information conversion from DTO is correct`() {
        with(deviceInformation.toDeviceInformation()) {
            assertThat(installOrigin.installOriginPackageName)
                .isEqualTo(deviceInformation.installOrigin.installOriginPackageName)

            assertThat(signatureVerification.signature)
                .isEqualTo(deviceInformation.signatureVerification.signatureVerificationString)

            assertThat(osCheck.board).isEqualTo(deviceInformation.osCheck.board)
            assertThat(osCheck.bootloader).isEqualTo(deviceInformation.osCheck.bootloader)
        }
    }
}
