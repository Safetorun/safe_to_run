package io.github.dllewellyn.safetorun.offdevice

import io.github.dllewellyn.safetorun.models.models.DeviceInformationDtoBuilder

/**
 * Builder for an off device result
 */
fun interface OffDeviceResultBuilder {
    /**
     * Build an off device result
     *
     * @param deviceSignatureDto the builder to pass in
     *
     * @return the builder after the new items have been added
     */
    fun buildOffDeviceResultBuilder(
        deviceSignatureDto: DeviceInformationDtoBuilder
    ): DeviceInformationDtoBuilder
}
