package com.safetorun.builder

import com.safetorun.inputverification.builders.model.AllowedTypeCore
import com.safetorun.inputverification.builders.model.ParameterConfig
import com.safetorun.inputverification.builders.model.SafeToRunInputVerification
import com.safetorun.inputverification.builders.model.UrlConfiguration
import com.safetorun.inputverification.builders.model.UrlConfigurations
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterSpec
import java.io.File

internal class SafeToRunCodeGenerator(
    private val outFile: File,
    private val inputConfiguration: SafeToRunInputVerification
) {

    private val registerFunction = MemberName("com.safetorun.intents.configurator", "register")

    @Suppress("DefaultLocale")
    fun generate() {
        val initClass = "SafeToRunInit"

        val initFunc = FunSpec.builder("initialiseSafeToRun")
            .addParameter(
                ParameterSpec.builder(
                    "context",
                    ClassName("android.content", "Context")
                ).build()
            )

        initFunc.addCode(
            CodeBlock.builder()
                .addStatement("%T(listOf(), listOf()).%M()", SafeToRunInputVerification::class, registerFunction)
                .build()
        )
        inputConfiguration.urlConfigurations.forEach {
            val codeBlock = CodeBlock.builder()

            val allowedParametersName = "allowedParameters"
            val allowedHostsName = "allowedHost"
            val allowedUrlsName = "allowedUrls"
            val configurationName = "configuration"

            codeBlock.beginControlFlow("run")

            generateListOfAllowedParameters(codeBlock, allowedParametersName, it)
            generateAllowedHosts(codeBlock, allowedHostsName, it)
            generateAllowedUrls(codeBlock, allowedUrlsName, it)

            codeBlock.addStatement(
                "val $configurationName = %T(${allowedHostsName}," +
                        "$allowedUrlsName," +
                        "${allowedParametersName}," +
                        "${it.configuration.allowAnyParameter}," +
                        "${it.configuration.allowAnyUrl})",
                UrlConfiguration::class
            ).addStatement(
                "%T(%S, $configurationName).%M()",
                UrlConfigurations::class,
                it.name,
                registerFunction
            )


            codeBlock.endControlFlow()
            initFunc.addCode(codeBlock.build())

        }

        val fileSpecBuilder = FileSpec.builder("com.safetorun", initClass)
            .addFunction(initFunc.build())

        inputConfiguration.urlConfigurations.forEach {
            fileSpecBuilder.addFunction(
                FunSpec.builder("verifyUrl%s".format(it.name.capitalize()))
                    .receiver(String::class)
                    .returns(Boolean::class)
                    .addStatement(
                        "return %M(%S)",
                        MemberName("com.safetorun.intents.configurator", "verifyUrl"),
                        it.name
                    )
                    .build()
            )
        }

        fileSpecBuilder.build().writeTo(outFile)
    }

    private fun generateAllowedHosts(
        codeBlock: CodeBlock.Builder,
        allowedHostsName: String,
        it: UrlConfigurations
    ) {
        codeBlock.addStatement("val $allowedHostsName = mutableListOf<%T>()", String::class)
        it.configuration.allowedHost.forEach { host ->
            codeBlock.addStatement("$allowedHostsName.add(%S)", host)
        }
    }

    private fun generateAllowedUrls(
        codeBlock: CodeBlock.Builder,
        allowedUrlsName: String,
        it: UrlConfigurations
    ) {
        codeBlock.addStatement("val $allowedUrlsName = mutableListOf<%T>()", String::class)
        it.configuration.allowedUrls.forEach { url ->
            codeBlock.addStatement("$allowedUrlsName.add(%S)", url)
        }
    }

    private fun generateListOfAllowedParameters(
        codeBlock: CodeBlock.Builder,
        allowedParametersName: String,
        it: UrlConfigurations
    ) {
        codeBlock.addStatement(
            "val $allowedParametersName = mutableListOf<%T>()",
            ParameterConfig::class
        )

        it.configuration.allowParameters.forEach { parameter ->
            codeBlock.addStatement(
                "${allowedParametersName}.add(%T(%S, %T.${parameter.allowedType}))",
                ParameterConfig::class,
                parameter.parameterName,
                AllowedTypeCore::class
            )
        }
    }

}
