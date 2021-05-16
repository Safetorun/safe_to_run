package io.github.dllewellyn.safetorun.backend.verifiers

import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT
import io.github.dllewellyn.safetorun.backend.generators.DefaultJwtGenerator
import io.github.dllewellyn.safetorun.backend.utils.JwtFactory
import io.github.dllewellyn.safetorun.models.models.VerifierResult

class DefaultJwtVerifier(
    private val apiKey: String,
    private val jwtFactory: JwtFactory
) : JwtVerifier {
    override fun verifyJwt(jwt: String): VerifierResult {

        return try {
            with(jwtFactory.verify(jwt)) {
                verifierResult(correctSignature = true)
            }
        } catch (e: SignatureVerificationException) {
            println(e)
            with(jwtFactory.decode(jwt)) {
                verifierResult(correctSignature = false)
            }
        } catch (e: TokenExpiredException) {
            println(e)
            with(jwtFactory.decode(jwt)) {
                verifierResult(correctSignature = false, expired = true)
            }
        }
    }

    private fun DecodedJWT.verifierResult(correctSignature: Boolean, expired: Boolean = false) = VerifierResult(
        correctIssuer = issuer == apiKey,
        anyFailures = (claims[DefaultJwtGenerator.Errors]?.asInt() ?: 1) > 0,
        correctSignature = correctSignature,
        expired = expired
    )
}
