package com.safetorun.builder

import com.safetorun.inputverification.builders.model.UrlConfigurations
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.MemberName


private val registerUrlRegisterFn
        by lazy { MemberName("com.safetorun.intents.configurator", "registerUrlVerification") }
private val paramConfig by lazy { ClassName("com.safetorun.intents.url.params", "ParameterConfig") }
private val allowedType by lazy { ClassName("com.safetorun.intents.url.params", "AllowedType") }

internal fun generateUrlConfigurationCode(urlConfigurations: UrlConfigurations): CodeBlock {

    val codeBuilder = CodeBlock.builder()
        .addStatement("%M(%S)", registerUrlRegisterFn, urlConfigurations.name)
        .beginControlFlow("")

    if (urlConfigurations.configuration.allowAnyUrl) {
        codeBuilder.addStatement("allowAnyUrl()")
    }
    if (urlConfigurations.configuration.allowAnyParameter) {
        codeBuilder.addStatement("allowAnyParameter()")
    }

    urlConfigurations.configuration.allowedHost.forEach {
        codeBuilder.addStatement(
            "%S.allowHost()",
            it
        )
    }
    urlConfigurations.configuration.allowedUrls.forEach {
        codeBuilder.addStatement(
            "%S.allowUrl()",
            it
        )
    }
    urlConfigurations.configuration.allowParameters.forEach {
        codeBuilder.addStatement(
            "allowParameter(%T(%S,%T.${it.allowedType}))",
            paramConfig,
            it.parameterName,
            allowedType
        )
    }

    return codeBuilder.endControlFlow().build()

}
