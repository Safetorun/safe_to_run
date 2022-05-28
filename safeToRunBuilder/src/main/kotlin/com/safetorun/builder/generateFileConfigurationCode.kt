package com.safetorun.builder

import com.safetorun.inputverification.builders.model.FileConfigurations
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.MemberName
import java.io.File

private val registerFileRegisterFn =
    MemberName("com.safetorun.intents.configurator", "registerFileVerification")


internal fun generateFileConfigurationCode(fileConfigurations: FileConfigurations): CodeBlock {
    val codeBuilder = CodeBlock.builder()
        .addStatement("%M(%S)", registerFileRegisterFn, fileConfigurations.name)
        .beginControlFlow("")

    codeBuilder.addStatement("allowAnyFile = ${fileConfigurations.configuration.allowAnyFile}")

    fileConfigurations.configuration.allowedExactFiles.forEach { allowedExactFile ->
        codeBuilder.addStatement(
            "%T(%S).allowExactFile()",
            File::class,
            allowedExactFile
        )
    }

    fileConfigurations.configuration.allowedParentDirectories.forEach { allowedParameter ->
        codeBuilder.addStatement(
            "%T(%S).allowDirectory(${allowedParameter.allowSubdirectories})",
            File::class,
            allowedParameter.directory
        )
    }

    return codeBuilder.endControlFlow()
        .build()
}