package io.github.dllewellyn.safetorun.backend.generators

import com.auth0.jwt.JWT
import io.github.dllewellyn.safetorun.models.models.SafeToRunResult
import io.github.dllewellyn.safetorun.backend.utils.ExpireTimeHandler
import io.github.dllewellyn.safetorun.backend.utils.JwtFactory
import javax.inject.Singleton

@Singleton
internal class DefaultJwtGenerator(private val jwtFactory: JwtFactory, private val expiryTime: ExpireTimeHandler) :
    JwtGenerator {

    override fun generateSecretFor(safeToRunResult: SafeToRunResult): String {
        return with(jwtFactory) {
            JWT.create()
                .withClaim(Warnings, safeToRunResult.warnings)
                .withClaim(Errors, safeToRunResult.failures)
                .withClaim(Passes, safeToRunResult.successes)
                .withIssuer(safeToRunResult.apiKey)
                .withSubject(safeToRunResult.deviceId)
                .withExpiresAt(expiryTime.getExpiryTime())
                .sign()
        }
    }

    companion object {
        const val Warnings = "warnings"
        const val Errors = "errors"
        const val Passes = "passes"
    }
}
