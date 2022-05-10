package com.safetorun.intents

import android.content.Intent
import android.os.Bundle

/**
 * Gather all intents inside a bundle
 *
 * @return list of intents that are found in the bundle
 */
fun Bundle?.gatherAllIntents(): List<Intent> {
    val returnList = mutableListOf<Intent>()

    this?.keySet()?.forEach {
        when (val containingKey = get(it)) {
            is Bundle -> returnList.addAll(containingKey.gatherAllIntents())
            is Intent -> returnList.add(containingKey)
        }
    }

    return returnList
}

