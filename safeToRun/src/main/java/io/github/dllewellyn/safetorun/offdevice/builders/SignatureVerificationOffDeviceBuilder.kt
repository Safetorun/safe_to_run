package io.github.dllewellyn.safetorun.offdevice.builders

import io.github.dllewellyn.safetorun.features.signatureverify.SignatureVerificationQuery
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDtoBuilder
import io.github.dllewellyn.safetorun.offdevice.OffDeviceResultBuilder

internal class SignatureVerificationOffDeviceBuilder(
    private val signatureVerificationQuery:
    SignatureVerificationQuery
) :
    OffDeviceResultBuilder {

    override fun buildOffDeviceResultBuilder(deviceSignatureDto: DeviceInformationDtoBuilder):
            DeviceInformationDtoBuilder {
        return deviceSignatureDto.apply {
            signatureVerificationQuery
                .retrieveSignatureForApplication()
                .let { signature(it) }
        }
    }
}
