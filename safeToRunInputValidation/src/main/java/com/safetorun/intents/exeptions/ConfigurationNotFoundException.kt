package com.safetorun.intents.exeptions

class ConfigurationNotFoundException(name: String) :
    Exception("$name not found in configurator") {
    companion object {
        fun newConfigurationNotFoundException(name: String): ConfigurationNotFoundException =
            ConfigurationNotFoundException(name)
    }
}
