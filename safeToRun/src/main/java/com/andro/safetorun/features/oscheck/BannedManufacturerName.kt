package com.andro.safetorun.features.oscheck

import com.andro.safetorun.conditional.Conditional
import java.util.*

class BannedManufacturerName(private val manufacturerName: String, private val osInformationQuery: OSInformationQuery) :
    Conditional {
    override fun invoke(): Boolean {
        return osInformationQuery.manufacturer().toUpperCase(Locale.ROOT) != manufacturerName.toUpperCase(Locale.ROOT)
    }

}

fun OSInformationQuery.notManufacturer(manufacturerName: String) = BannedManufacturerName(manufacturerName, this)

