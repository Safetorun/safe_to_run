package io.github.dllewellyn.safetorun.offdevice.builders

import com.safetorun.features.signatureverify.SignatureVerificationQuery
import com.safetorun.models.builders.DeviceInformationDtoBuilder
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
