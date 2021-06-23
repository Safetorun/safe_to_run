package io.github.dllewellyn.safetorun.intents

import android.content.Intent
import android.os.Bundle


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

