package com.safetorun.builder

import com.google.common.truth.Truth.assertThat
import com.safetorun.builder.input.generateUrlConfigurationCode
import com.safetorun.inputverification.model.AllowedTypeCore
import com.safetorun.inputverification.model.ParameterConfig
import com.safetorun.inputverification.model.UrlConfiguration
import com.safetorun.inputverification.model.UrlConfigurations
import org.junit.Test

internal class UrlConfigurationCodeGeneratorTest {

    @Test
    fun `test that url code generator will generate correct code`() {
        val urlConfigurations = UrlConfigurations(
            name = "configName",
            UrlConfiguration(
                allowedHost = listOf("allowedHost"),
                allowedUrls = listOf("allowedUrl"),
                allowAnyUrl = true,
                allowAnyParameter = true,
                allowParameters = listOf(
                    ParameterConfig(
                        "parameterName",
                        AllowedTypeCore.Boolean
                    )
                )
            )
        )

        val configurationCode = generateUrlConfigurationCode(urlConfigurations).toString()
        val param = urlConfigurations.configuration.allowParameters.first()

        val allowParam =
            "com.safetorun.intents.url.params.ParameterConfig(\"${param.parameterName}\"," +
                    "com.safetorun.intents.url.params.AllowedType.Boolean)"

        assertThat(configurationCode).isEqualTo(
            """
 com.safetorun.intents.configurator.registerUrlVerification("${urlConfigurations.name}")
  {
   allowAnyUrl()
   allowAnyParameter()
   "${urlConfigurations.configuration.allowedHost.first()}".allowHost()
   "${urlConfigurations.configuration.allowedUrls.first()}".allowUrl()
   allowParameter($allowParam)
 }

        """.trimIndent()
        )
    }

    @Test
    fun `test that url code generator will generate correct code when allow any url and param set to false`() {
        val urlConfigurations = UrlConfigurations(
            name = "configName",
            UrlConfiguration(
                allowedHost = listOf("allowedHost", "allowOtherHost"),
                allowedUrls = listOf("allowedUrl"),
                allowAnyUrl = false,
                allowAnyParameter = false,
                allowParameters = listOf(
                    ParameterConfig(
                        "parameterName",
                        AllowedTypeCore.Boolean
                    )
                )
            )
        )

        val configurationCode = generateUrlConfigurationCode(urlConfigurations).toString()
        val param = urlConfigurations.configuration.allowParameters.first()

        val allowParam =
            "com.safetorun.intents.url.params.ParameterConfig(\"${param.parameterName}\"," +
                    "com.safetorun.intents.url.params.AllowedType.Boolean)"
        assertThat(configurationCode).isEqualTo(
            """
 com.safetorun.intents.configurator.registerUrlVerification("${urlConfigurations.name}")
  {
   "${urlConfigurations.configuration.allowedHost.first()}".allowHost()
   "${urlConfigurations.configuration.allowedHost.last()}".allowHost()
   "${urlConfigurations.configuration.allowedUrls.first()}".allowUrl()
   allowParameter($allowParam)
 }

        """.trimIndent()
        )
    }
}
