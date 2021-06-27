package io.github.dllewellyn.safetorun.intents.url.params

/**
 * Configuration which will allow a URL parameter to be allowed
 */
data class ParameterConfig(val parameterName: String, val allowedType: AllowedType)
