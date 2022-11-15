package com.safetorun.builder

import com.safetorun.inputverification.dto.SafeToRunInputVerificationDto
import com.safetorun.inputverification.dto.toCore
import com.safetorun.inputverification.model.SafeToRunInputVerification

fun SafeToRunInputVerificationDto?.toCore(): SafeToRunInputVerification =
    if (this != null) {
        SafeToRunInputVerification(
            urlConfigurations = urlConfigurations.map { conf -> conf.toCore() },
            fileConfiguration = fileConfiguration.map { conf -> conf.toCore() },
        )
    } else {
        SafeToRunInputVerification(emptyList(), emptyList())
    }
