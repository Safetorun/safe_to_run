package com.safetorun.builder.resilience

import com.safetorun.resilienceshared.dto.IntCheckType
import com.safetorun.resilienceshared.dto.OnDeviceResilienceDto
import com.safetorun.resilienceshared.dto.SingleIntCheck
import com.safetorun.resilienceshared.dto.SingleStringCheck
import com.safetorun.resilienceshared.dto.StringCheckType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberName

internal class ResilienceCodeGenerator(private val resilienceDto: OnDeviceResilienceDto) {

    private val safeToRunFun
            by lazy { MemberName("com.safetorun.inline", "safeToRun") }

    private val banDebugCheck
            by lazy { MemberName("com.safetorun.features.debug", "banDebugCheck") }

    private val context by lazy { ClassName("android.content", "Context") }

    private val signatureVerify by lazy {
        MemberName("com.safetorun.features.signatureverify", "verifySignatureCheck")
    }

    private val blacklistedApps by lazy {
        MemberName("com.safetorun.features.blacklistedapps", "blacklistedAppCheck")
    }
    private val installOrigin by lazy {
        MemberName("com.safetorun.features.installorigin", "installOriginCheckWithoutDefaultsCheck")
    }

    private val rootDetectionCheck by lazy {
        MemberName("com.safetorun.features.rootdetection", "rootDetectionCheck")
    }

    private val safeToRunCombinedCheck by lazy {
        MemberName("com.safetorun.features.oscheck", "safeToRunCombinedCheck")
    }

    fun build(): FunSpec {
        val checksBuilder = CodeBlock.builder()
            .addStatement("val checks = %M(", safeToRunFun)
            .apply {
                addDebuggerCheck()
                addRootCheck()
                addBlacklistedAppChecks()
                addOsCheck()
                addAllowedSignaturesCheck()
                addInstallOriginCheck()
            }
            .addStatement(")")
            .addStatement("return checks()")



        return FunSpec.builder("verifyDevice")
            .receiver(context)
            .addModifiers(KModifier.INLINE)
            .addCode(checksBuilder.build())
            .returns(ClassName("kotlin", "Boolean"))
            .build()
    }

    private fun CodeBlock.Builder.addInstallOriginCheck() {
        resilienceDto.installOriginCheck?.let {
            beginControlFlow("")
            addStatement(
                "%M(${it.joinToString(",") { installOrigin -> "\"$installOrigin\"" }})",
                installOrigin
            )
            endControlFlow()
            addStatement(",")
        }
    }

    private fun CodeBlock.Builder.addAllowedSignaturesCheck() {
        resilienceDto.allowedSignatures?.let {
            beginControlFlow("")
            addStatement(
                "%M(${it.joinToString(",") { signature -> "\"$signature\"" }})",
                signatureVerify
            )
            endControlFlow()
            addStatement(",")
        }
    }

    private fun CodeBlock.Builder.addOsCheck() {
        resilienceDto.osCheckConfiguration?.onEach {
            beginControlFlow("")
            addStatement("%M(", safeToRunCombinedCheck)
            addStatement("listOf(")
            it.allIntChecks.forEach { singleIntCheck ->
                intCheckToStatement(singleIntCheck)
            }
            it.allStringChecks.forEach { singleStringCheck ->
                stringCheckToStatement(singleStringCheck)
            }
            addStatement("),")

            addStatement("listOf(")
            it.unlessIntChecks.forEach { singleIntCheck ->
                intCheckToStatement(singleIntCheck)
            }
            it.unlessStringChecks.forEach { singleStringCheck ->
                stringCheckToStatement(singleStringCheck)
            }
            addStatement(")")

            addStatement(")")
            endControlFlow()
            addStatement(",")
        }
    }

    private fun CodeBlock.Builder.addBlacklistedAppChecks() {
        resilienceDto.blacklistedApps?.let {
            beginControlFlow("")
            addStatement(
                "%M(${it.joinToString(",") { blacklistedApp -> "\"$blacklistedApp\"" }})",
                blacklistedApps
            )
            endControlFlow()
            addStatement(",")
        }
    }

    private fun CodeBlock.Builder.addDebuggerCheck() {
        if (resilienceDto.banDebugger) {
            beginControlFlow("")
            addStatement("%M()", banDebugCheck)
            endControlFlow()
            addStatement(",")
        }
    }

    private fun CodeBlock.Builder.addRootCheck() {
        resilienceDto.rootCheck?.let {
            beginControlFlow("")
            addStatement(
                "%M(${it.tolerateBusyBox})",
                rootDetectionCheck
            )
            endControlFlow()
            addStatement(",")
        }
    }

    private fun CodeBlock.Builder.stringCheckToStatement(singleStringCheck: SingleStringCheck) {
        addStatement(
            "{ %M(\"${singleStringCheck.stringValue}\") },",
            MemberName(OS_CHECK_PACKAGE, singleStringCheck.checkType.toFunctionName())
        )
    }

    private fun CodeBlock.Builder.intCheckToStatement(it: SingleIntCheck) {
        when (it.checkType) {
            IntCheckType.MinOsCheck -> {
                addStatement(
                    "{ %M(${it.intValue}) },",
                    MemberName(OS_CHECK_PACKAGE, "minOsVersionCheck")
                )
            }
        }
    }

    private fun StringCheckType.toFunctionName() = when (this) {
        StringCheckType.BannedBoard -> "bannedBoardCheck"
        StringCheckType.BannedBootloader -> "bannedBootloaderCheck"
        StringCheckType.BannedCpuAbi -> "bannedCpusCheck"
        StringCheckType.BannedDevice -> "bannedDeviceCheck"
        StringCheckType.BannedHardware -> "bannedHardwareCheck"
        StringCheckType.BannedHost -> "bannedHostCheck"
        StringCheckType.BannedModel -> "bannedModelCheck"
    }

    companion object {
        const val OS_CHECK_PACKAGE = "com.safetorun.features.oscheck"
    }
}
