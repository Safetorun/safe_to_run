package com.safetorun.intents.url.params

/**
 * Use to configure a type which is allowed
 */
enum class AllowedType {
    /**
     * Any type allowed
     */
    Any,

    /**
     * Only string allowed
     */
    String,

    /**
     * Only boolean allowed
     */
    Boolean,

    /**
     * Only int allowed
     */
    Int,

    /**
     * Only double allowed
     */
    Double
}

internal fun AllowedType.matchesAllowedType(input: String?) = input?.let {
    when (this) {
        AllowedType.Any -> true
        AllowedType.String -> true
        AllowedType.Boolean -> input.equals("true", true) ||
                input.equals("false", true)
        AllowedType.Int -> input.toIntOrNull() != null
        AllowedType.Double -> input.toDoubleOrNull() != null
    }
} ?: false
