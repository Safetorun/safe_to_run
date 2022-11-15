package com.safetorun.builder.input

import com.safetorun.SafeToRunConfiguration
import com.safetorun.builder.generateFileConfigurationCode
import com.safetorun.builder.resilience.ResilienceCodeGenerator
import com.safetorun.builder.toCore
import com.safetorun.inputverification.model.SafeToRunInputVerification
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterSpec
import java.io.File


internal fun generate(
    outFile: File,
    configuration: SafeToRunConfiguration
) {
    val initClass = "SafeToRunInit"

    val initFunc = FunSpec.builder("initialiseSafeToRun")
        .addParameter(
            ParameterSpec.builder(
                "context",
                ClassName("android.content", "Context")
            ).build()
        )

    val inputConfiguration = configuration.inputVerification.toCore()


    inputConfiguration.fileConfiguration
        .map(::generateFileConfigurationCode)
        .forEach(initFunc::addCode)

    inputConfiguration.urlConfigurations
        .map(::generateUrlConfigurationCode)
        .forEach(initFunc::addCode)

    val fileSpecBuilder = FileSpec
        .builder("com.safetorun", initClass)
        .addFunction(initFunc.build())


    configuration.ondeviceResilience?.let {
        fileSpecBuilder.addFunction(
            ResilienceCodeGenerator(it)
                .build()
        )
    }

    addUrlConfigurations(inputConfiguration, fileSpecBuilder)
    addFileConfigurations(inputConfiguration, fileSpecBuilder)

    fileSpecBuilder.build().writeTo(outFile)
}

private fun addFileConfigurations(
    inputConfiguration: SafeToRunInputVerification,
    fileSpecBuilder: FileSpec.Builder
) {
    inputConfiguration.fileConfiguration.forEach {
        fileSpecBuilder.addFunction(
            FunSpec.builder("verifyFile%s".format(it.name.capitalize()))
                .receiver(File::class)
                .returns(Boolean::class)
                .addStatement(
                    "return %M(%S)",
                    MemberName("com.safetorun.intents.configurator", "verifyFile"),
                    it.name
                )
                .build()
        )
    }
}

private fun addUrlConfigurations(
    inputConfiguration: SafeToRunInputVerification,
    fileSpecBuilder: FileSpec.Builder
): FileSpec.Builder {
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

    return fileSpecBuilder
}

