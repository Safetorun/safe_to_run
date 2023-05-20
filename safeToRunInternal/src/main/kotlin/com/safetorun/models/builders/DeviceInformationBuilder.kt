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
    private var _isRooted: Boolean = false

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
     * Add if device is rooted
     */
    override fun isRooted(isRooted: Boolean) {
        _isRooted = isRooted
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
            _signature,
            _isRooted
        )
    }

    private fun buildForUnwrappedValues(
        osVersionCheck: OsCheck,
        installOrigin: String,
        signature: String,
        isRooted: Boolean
    ) =
        DeviceInformation(
            osCheck = osVersionCheck,
            installOrigin = InstallOrigin(installOrigin),
            blacklistedApp = BlacklistedApps(
                installedPackages = _installedApplications
            ),
            signatureVerification = DeviceSignature(
                signature = signature
            ),
            isRooted = isRooted
        )
}
