package io.github.dllewellyn.safetorun.backend.util

import io.github.dllewellyn.safetorun.backend.repository.JwtSecretRepository
import io.micronaut.context.annotation.Primary
import io.mockk.every
import io.mockk.mockk
import javax.inject.Singleton

val mockkSecretManager = mockk<JwtSecretRepository>()
    .apply {
        every { getJwtSecret() } returns "Abcdefhijkasdlasdasdasd"
    }

@Primary
@Singleton
internal class SimpleMockSecret : JwtSecretRepository {
    override fun getJwtSecret(): String {
        return "Abcdefhijkasdlasdasdasd"
    }
}
