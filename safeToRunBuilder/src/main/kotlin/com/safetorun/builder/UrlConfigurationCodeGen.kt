package com.safetorun.builder

import com.safetorun.inputverification.builders.model.UrlConfigurations
import com.squareup.kotlinpoet.CodeBlock

fun generateSingleUrlInitialisationCode(@Suppress("UNUSED_PARAMETER") configuration: UrlConfigurations) =
    CodeBlock.builder().build()

fun generateUrlListInitialisationCode(
    configurations: List<UrlConfigurations>,
    singleUrlConfigurationGenerator: (UrlConfigurations) -> CodeBlock = ::generateSingleUrlInitialisationCode
): CodeBlock {
    val codeBlockBuilder = CodeBlock.builder()

    configurations.forEach {
        codeBlockBuilder.add(singleUrlConfigurationGenerator(it))
    }

    return codeBlockBuilder.build()
}
