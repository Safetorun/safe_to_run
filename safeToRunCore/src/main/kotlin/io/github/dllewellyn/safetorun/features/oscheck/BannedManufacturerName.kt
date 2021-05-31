package io.github.dllewellyn.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.conditional.ConditionalResponse

internal class BannedManufacturerName(
    private val manufacturerName: String,
    private val osInformationQuery: OSInformationQuery
) :
    Conditional {
    override fun invoke(): ConditionalResponse {
        return ConditionalResponse(
            failed = osInformationQuery.manufacturer().equals(manufacturerName, ignoreCase = true),
            message = "${osInformationQuery.manufacturer()} == $manufacturerName"
        )
    }
}

/**
 * Use a check to verify that the device is not from a particular manufacturer
 *
 * @param manufacturerName the manufacturer you want to check for
 *
 * @return a conditional to be used with os check
 */
fun OSInformationQuery.notManufacturer(manufacturerName: String): Conditional =
    BannedManufacturerName(manufacturerName, this)
