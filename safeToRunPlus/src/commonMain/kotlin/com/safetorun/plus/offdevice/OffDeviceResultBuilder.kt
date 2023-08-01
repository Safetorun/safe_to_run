package com.safetorun.plus.offdevice

import com.safetorun.plus.builders.DeviceInformationDtoBuilder

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
