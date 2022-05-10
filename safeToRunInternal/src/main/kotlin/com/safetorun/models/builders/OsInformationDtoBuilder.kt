package com.safetorun.models.builders

import com.safetorun.models.models.OsCheckDto
import com.safetorun.models.models.OsHardwareInformation

internal class OsInformationDtoBuilder : IOsInformationDtoBuilder,
    IOsHardwareInformationBuilder by OsHardwareInformationBuilder() {

    private var _bootloader: String? = null
    private var _host: String? = null
    private var _manufacturer: String? = null
    private var _model: String? = null
    private var _osVersion: String? = null
    private var _device: String? = null

    /**
     * Add host
     */
    override fun host(host: String): OsInformationDtoBuilder {
        this._host = host
        return this
    }

    /**
     * Add bootloader
     */
    override fun bootloader(bootloader: String): OsInformationDtoBuilder {
        this._bootloader = bootloader
        return this
    }

    /**
     * Add os version
     */
    override fun osVersion(osVersion: String) {
        _osVersion = osVersion
    }

    /**
     * Add manufacturer
     */
    override fun manufacturer(manufacturer: String) {
        _manufacturer = manufacturer
    }

    /**
     * Add model
     */
    override fun model(model: String) {
        _model = model
    }

    /**
     * Add a device
     */
    override fun device(device: String) {
        this._device = device
    }

    override fun buildOsCheck(): OsCheckDto {
        val osVersion = unwrapOrThrow(_osVersion, "Os version")
        val manufacturer = unwrapOrThrow(_manufacturer, "Manufacturer")
        val model = unwrapOrThrow(_model, "Model")
        val bootloader: String = unwrapOrThrow(_bootloader, "Bootloader")
        val host: String = unwrapOrThrow(_host, "Host")
        val device: String = unwrapOrThrow(_device, "Device")
        return buildOsCheckDto(host, bootloader, device)
            .addValues(
                buildPartialHardwareInformation(),
                osVersion,
                manufacturer,
                model
            )
    }

    override fun buildPartialOsCheck(): OsCheckDto {
        val osVersion = _osVersion ?: ""
        val manufacturer = _manufacturer ?: ""
        val model = _model ?: ""
        val bootloader: String = _bootloader ?: ""
        val host: String = _host ?: ""
        val device: String = _device ?: ""

        return buildOsCheckDto(host, bootloader, device)
            .addValues(
                buildPartialHardwareInformation(),
                osVersion,
                manufacturer,
                model
            )
    }

    private fun OsCheckDto.addValues(
        hardwareInformation: OsHardwareInformation,
        osVersion: String,
        manufacturer: String,
        model: String,
    ) = apply {
        this.osVersion = osVersion
        this.manufacturer = manufacturer
        this.model = model
        this.cpuAbi = hardwareInformation.cpuAbis
        this.board = hardwareInformation.board
        this.hardware = hardwareInformation.hardware
    }

    private fun buildOsCheckDto(
        host: String,
        bootloader: String,
        device: String
    ): OsCheckDto {
        return OsCheckDto().apply {
            this.host = host
            this.bootloader = bootloader
            this.device = device
        }
    }
}
