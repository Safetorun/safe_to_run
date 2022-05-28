package com.safetorun.builder

import com.safetorun.inputverification.builders.model.SafeToRunInputVerification
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterSpec
import java.io.File


fun generate(
    outFile: File,
    inputConfiguration: SafeToRunInputVerification
) {
    val initClass = "SafeToRunInit"

    val initFunc = FunSpec.builder("initialiseSafeToRun")
        .addParameter(
            ParameterSpec.builder(
                "context",
                ClassName("android.content", "Context")
            ).build()
        )

    inputConfiguration.fileConfiguration
        .map(::generateFileConfigurationCode)
        .forEach(initFunc::addCode)

    inputConfiguration.urlConfigurations
        .map(::generateUrlConfigurationCode)
        .forEach(initFunc::addCode)

    val fileSpecBuilder = FileSpec
        .builder("com.safetorun", initClass)
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

    fileSpecBuilder.build().writeTo(outFile)
}

