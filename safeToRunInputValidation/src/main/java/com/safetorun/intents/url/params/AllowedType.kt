package com.safetorun.intents.url.params

import com.safetorun.intents.url.UrlConfigVerifier
import com.safetorun.intents.url.urlVerification

/**
 * Use to configure a type which is allowed
 */
sealed class AllowedType {
    /**
     * Any type allowed
     */
    object Any : AllowedType()

    /**
     * Only string allowed
     */
    object String : AllowedType()

    /**
     * Only boolean allowed
     */
    object Boolean : AllowedType()

    /**
     * Only int allowed
     */
    object Int : AllowedType()

    /**
     * Only double allowed
     */
    object Double : AllowedType()

    /**
     * Allow verification of a URL
     */
    class Url(val configuration: UrlConfigVerifier) : AllowedType()
}

internal fun AllowedType.matchesAllowedType(input: String?) = input?.let {
    when (this) {
        AllowedType.Any -> true
        AllowedType.String -> true
        AllowedType.Boolean -> input.equals("true", true) ||
                input.equals("false", true)
        AllowedType.Int -> input.toIntOrNull() != null
        AllowedType.Double -> input.toDoubleOrNull() != null
        is AllowedType.Url -> input.urlVerification(configuration)
    }
} ?: false
