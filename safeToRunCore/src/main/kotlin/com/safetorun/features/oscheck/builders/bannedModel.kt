package com.safetorun.features.oscheck.builders

import com.safetorun.conditional.Conditional
import com.safetorun.features.oscheck.OSInformationQuery
import com.safetorun.features.oscheck.baseOsCheck/**
 * Add a banned model to the list
 *
 * @param bannedModel the model to ban
 */
fun OSInformationQuery.bannedModel(bannedModel: String): Conditional =
    baseOsCheck({ "OSInformation ${model()} == $bannedModel" }) {
        model() == bannedModel
    }
