package com.safetorun

import com.safetorun.backendresilience.BackendResilienceBuilder
import com.safetorun.backendresilience.dto.BackendResilienceDto
import com.safetorun.inputverification.InputVerificationBuilder
import com.safetorun.inputverification.dto.SafeToRunInputVerificationDto
import com.safetorun.resilienceshared.OnDeviceResilienceBuilder
import com.safetorun.resilienceshared.dto.OnDeviceResilienceDto
import kotlinx.serialization.json.Json

/**
 * Safe to run configuration
 */
@kotlinx.serialization.Serializable
data class SafeToRunConfiguration internal constructor(
    val backendResilience: BackendResilienceDto? = null,
    val inputVerification: SafeToRunInputVerificationDto? = null,
    val ondeviceResilience: OnDeviceResilienceDto? = null,
)

/**
 * Safe to run configuration builder
 */
class SafeToRunConfigurationBuilder internal constructor() {
    private var backendResilience: BackendResilienceDto = BackendResilienceDto()
    private var inputVerification: SafeToRunInputVerificationDto = SafeToRunInputVerificationDto()
    private var ondeviceResilience: OnDeviceResilienceDto = OnDeviceResilienceDto()

    /**
     * Add backend resilience configuration
     *
     * @param builder builder for backend resilience
     */
    fun backendResilience(builder: BackendResilienceBuilder.() -> Unit) {
        backendResilience = BackendResilienceBuilder()
            .apply(builder)
            .build()
    }

    /**
     * Add an on device resilience check
     */
    fun onDeviceResilience(builder: OnDeviceResilienceBuilder.() -> Unit) {
        ondeviceResilience = OnDeviceResilienceBuilder().apply(builder).build()
    }

    /**
     * Add inputVerification configuration
     *
     * @param builder builder for input verification
     */
    fun inputVerification(builder: InputVerificationBuilder.() -> Unit) {
        inputVerification = InputVerificationBuilder()
            .apply(builder)
            .build()
    }

    internal fun build() =
        SafeToRunConfiguration(backendResilience, inputVerification, ondeviceResilience)
}

/**
 * Safe to run builder
 */
fun safeToRun(builder: SafeToRunConfigurationBuilder.() -> Unit): SafeToRunConfiguration =
    SafeToRunConfigurationBuilder()
        .apply(builder)
        .build()

fun main() {
    val res = safeToRun {
        onDeviceResilience {
            allowDebugger()
        }
    }

    println(Json.encodeToString(SafeToRunConfiguration.serializer(), res))
}
