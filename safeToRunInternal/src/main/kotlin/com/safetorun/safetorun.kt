package com.safetorun

import com.safetorun.backendresilience.BackendResilienceBuilder
import com.safetorun.backendresilience.dto.BackendResilience
import com.safetorun.inputverification.InputVerificationBuilder
import com.safetorun.inputverification.dto.SafeToRunInputVerificationDto
import com.safetorun.resilienceshared.dto.OnDeviceResilience

/**
 * Safe to run configuration
 */
@kotlinx.serialization.Serializable
data class SafeToRunConfiguration internal constructor(
    val backendResilience: BackendResilience? = null,
    val inputVerification: SafeToRunInputVerificationDto? = null,
    val ondeviceResilience: OnDeviceResilience? = null,
)

/**
 * Safe to run configuration builder
 */
class SafeToRunConfigurationBuilder internal constructor() {
    private var backendResilience: BackendResilience = BackendResilience()
    private var inputVerification: SafeToRunInputVerificationDto = SafeToRunInputVerificationDto()
    private var ondeviceResilience: OnDeviceResilience = OnDeviceResilience()

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
    fun onDeviceResilience(builder: BackendResilienceBuilder.() -> Unit) {
        BackendResilienceBuilder().apply(builder).build()
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
