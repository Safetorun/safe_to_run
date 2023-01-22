package com.safetorun.models.builders

import com.safetorun.models.core.BlacklistedApps
import com.safetorun.models.core.DeviceInformation
import com.safetorun.models.core.DeviceSignature
import com.safetorun.models.core.InstallOrigin
import com.safetorun.models.core.OsCheck


internal class DeviceInformationBuilder(
    private val osInformation: IOsInformationDtoBuilder = OsInformationDtoBuilder()
) : IOsInformationDtoBuilder by osInformation, IDeviceInformationBuilder {

    private val _installedApplications: MutableList<String> = mutableListOf()
    private var _signature: String = ""
    private var _installOrigin: String = ""


    /**
     * Add installed application
     */
    override fun installedApplication(packageName: String) {
        _installedApplications.add(packageName)
    }

    /**
     * Add install origin
     */
    override fun installOrigin(installOrigin: String) {
        _installOrigin = installOrigin
    }

    /**
     * Add signature
     */
    override fun signature(signature: String) {
        _signature = signature
    }

    /**
     * Build a full device information DTO. Will exception if
     * there are missing values not added
     *
     * @return a device information object
     * @throws IllegalArgumentException if any items are null
     */
    internal fun build(): DeviceInformation {
        return buildForUnwrappedValues(
            osInformation.buildOsCheck(),
            _installOrigin,
            _signature
        )
    }

    private fun buildForUnwrappedValues(
        osVersionCheck: OsCheck,
        installOrigin: String,
        signature: String
    ) =
        DeviceInformation(
            osCheck = osVersionCheck,
            installOrigin = InstallOrigin(installOrigin),
            blacklistedApp = BlacklistedApps(
                installedPackages = _installedApplications
            ),
            signatureVerification = DeviceSignature(
                signature = signature
            )
        )
}
