package com.safetorun.models.builders

import com.safetorun.models.core.OsCheck

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

    override fun buildOsCheck(): OsCheck {
        val osVersion = _osVersion ?: ""
        val manufacturer = _manufacturer ?: ""
        val model = _model ?: ""
        val bootloader: String = _bootloader ?: ""
        val host: String = _host ?: ""
        val device: String = _device ?: ""

        return buildHardwareInformation().run {
            OsCheck(
                osVersion,
                manufacturer,
                model,
                board,
                bootloader,
                cpuAbis,
                host,
                hardware,
                device
            )
        }
    }
}
