package io.github.dllewellyn.safetorun.backend.util

import io.github.dllewellyn.safetorun.backend.repository.JwtSecretRepository
import io.mockk.every
import io.mockk.mockk

val mockkSecretManager = mockk<JwtSecretRepository>()
    .apply {
        every { getJwtSecret() } returns "Abcdefhijkasdlasdasdasd"
    }
