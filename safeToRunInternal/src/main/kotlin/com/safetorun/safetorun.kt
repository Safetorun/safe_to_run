package com.safetorun

import com.safetorun.backendresilience.BackendResilienceBuilder
import com.safetorun.backendresilience.dto.BackendResilience
import com.safetorun.inputverification.InputVerificationBuilder
import com.safetorun.inputverification.dto.SafeToRunInputVerificationDto

/**
 * Safe to run configuration
 */
@kotlinx.serialization.Serializable
data class SafeToRunConfiguration internal constructor(
    val backendResilience: BackendResilience? = null,
    val inputVerification: SafeToRunInputVerificationDto? = null,
)

class SafeToRunConfigurationBuilder internal constructor() {
    private var backendResilience: BackendResilience = BackendResilience()
    private var inputVerification: SafeToRunInputVerificationDto = SafeToRunInputVerificationDto()

    fun backendResilience(builder: BackendResilienceBuilder.() -> Unit) {
        backendResilience = BackendResilienceBuilder()
            .apply(builder)
            .build()
    }

    fun inputVerification(builder: InputVerificationBuilder.() -> Unit) {
        inputVerification = InputVerificationBuilder()
            .apply(builder)
            .build()
    }

    internal fun build() = SafeToRunConfiguration(backendResilience, inputVerification)
}

fun safeToRun(builder: SafeToRunConfigurationBuilder.() -> Unit): SafeToRunConfiguration =
    SafeToRunConfigurationBuilder()
        .apply(builder)
        .build()


fun main() {
    safeToRun {
        inputVerification {

        }

        backendResilience {

        }
    }
}
