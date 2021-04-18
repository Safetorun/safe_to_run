package com.andro.safetorun.features.oscheck

import com.andro.safetorun.conditional.Conditional
import com.andro.safetorun.conditional.ConditionalResponse
import java.util.*

class BannedManufacturerName(private val manufacturerName: String, private val osInformationQuery: OSInformationQuery) :
    Conditional {
    override fun invoke(): ConditionalResponse {
        return ConditionalResponse(
            failed = osInformationQuery.manufacturer().equals(manufacturerName, ignoreCase = true),
            message = "${osInformationQuery.manufacturer()} == $manufacturerName"
        )
    }
}

fun OSInformationQuery.notManufacturer(manufacturerName: String) = BannedManufacturerName(manufacturerName, this)

