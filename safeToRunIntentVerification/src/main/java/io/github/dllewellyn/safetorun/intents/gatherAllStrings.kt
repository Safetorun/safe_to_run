package io.github.dllewellyn.safetorun.intents

import android.os.Bundle

/**
 * Gather all strings inside a bundle
 *
 * @return list of strings that are found in the bundle
 */
fun Bundle?.gatherAllStrings(): List<String> {
    val returnList = mutableListOf<String>()

    this?.keySet()?.forEach {
        when (val containingKey = get(it)) {
            is Bundle -> returnList.addAll(containingKey.gatherAllStrings())
            is String -> returnList.add(containingKey)
            is List<*> -> returnList.addAll(containingKey.filterIsInstance<String>())
            is Array<*> -> returnList.addAll(containingKey.filterIsInstance<String>())
        }
    }

    return returnList
}

