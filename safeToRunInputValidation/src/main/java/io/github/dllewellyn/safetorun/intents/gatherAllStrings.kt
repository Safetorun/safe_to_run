package io.github.dllewellyn.safetorun.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable


/**
 * Gather all strings inside an intent
 *
 * @return list of strings that are found in the bundle
 */
fun Intent?.gatherAllStrings(): List<String> {
    return (this?.data?.let {
        mutableListOf(it.toString())
    } ?: mutableListOf()).also {
        it.addAll(this?.extras?.gatherAllStrings() ?: emptyList())
    }

}

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
            is List<*> -> {
                returnList.addAll(containingKey.filterIsInstance<String>())
                returnList.addAll(containingKey.filterIsInstance<Uri>().map { uri -> uri.toString() })
            }
            is Parcelable -> if ((containingKey is Uri)) {
                returnList.add(containingKey.toString())
            }
            is Array<*> -> returnList.addAll(containingKey.filterIsInstance<String>())
        }
    }

    return returnList
}

