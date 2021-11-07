package io.github.dllewellyn.safetorun.features.oscheck.builders

import io.github.dllewellyn.safetorun.conditional.Conditional
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQuery
import io.github.dllewellyn.safetorun.features.oscheck.baseOsCheck/**
 * Add a banned model to the list
 *
 * @param bannedModel the model to ban
 */
fun OSInformationQuery.bannedModel(bannedModel: String): Conditional =
    baseOsCheck({ "OSInformation ${model()} == $bannedModel" }) {
        model() == bannedModel
    }
