package com.safetorun.intents.exeptions

/**
 * Exception for when the configuration is looked up in a configuration, and can't be found
 */
class ConfigurationNotFoundException internal constructor(name: String) :
    Exception("$name not found in configurator") {
    companion object {
        /**
         * Raise a new exception for a configuration name
         *
         * @param name name of the configuration that was searched for
         */
        fun newConfigurationNotFoundException(name: String): ConfigurationNotFoundException =
            ConfigurationNotFoundException(name)
    }
}
