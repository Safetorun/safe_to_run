package com.safetorun.intents.url.params

/**
 * Build a configuration which will allow a URL parameter to be allowed
 */
class ParameterConfigBuilder {
    /**
     * Parameter name to allow
     */
    var parameterName: String? = null

    /**
     * The type that is allowed is for this parameter
     */
    var allowedType = AllowedType.Any
}
