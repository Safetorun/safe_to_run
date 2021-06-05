package io.github.dllewellyn.safetorun.exploration

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.api.deviceInformation
import io.github.dllewellyn.safetorun.models.models.InstallOriginDto
import io.github.dllewellyn.safetorun.models.models.SignatureVerificationDto
import junit.framework.TestCase

internal class DeviceInformationKtTest : TestCase() {

    fun `test that device information conversion from DTO is correct with null values`() {
        deviceInformation.copy(
            signatureVerification = SignatureVerificationDto(),
            installOrigin = InstallOriginDto()
        ).toDeviceInformation().apply {
            assertThat(installOrigin.installOriginPackageName).isNotNull()
            assertThat(signatureVerification.signature).isNotNull()
        }
    }

    fun `test that device information conversion from DTO is correct`() {
        with(deviceInformation.toDeviceInformation()) {
            assertThat(installOrigin.installOriginPackageName)
                .isEqualTo(deviceInformation.installOrigin.installOriginPackageName)

            assertThat(signatureVerification.signature)
                .isEqualTo(deviceInformation.signatureVerification.signatureVerificationString)

            assertThat(osCheck.model).isEqualTo(deviceInformation.osCheck.model)
            assertThat(osCheck.osVersion).isEqualTo(deviceInformation.osCheck.osVersion)
            assertThat(osCheck.board).isEqualTo(deviceInformation.osCheck.board)
            assertThat(osCheck.bootloader).isEqualTo(deviceInformation.osCheck.bootloader)
            assertThat(osCheck.manufacturer).isEqualTo(deviceInformation.osCheck.manufacturer)
            assertThat(osCheck.cpuAbi).isEqualTo(deviceInformation.osCheck.cpuAbi)
            assertThat(osCheck.host).isEqualTo(deviceInformation.osCheck.host)
            assertThat(osCheck.hardware).isEqualTo(deviceInformation.osCheck.hardware)
            assertThat(osCheck.device).isEqualTo(deviceInformation.osCheck.device)
        }
    }
}
