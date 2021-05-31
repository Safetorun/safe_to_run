package io.github.dllewellyn.safetorun.backend.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import io.github.dllewellyn.safetorun.backend.repository.JwtSecretRepository
import javax.inject.Singleton

@Singleton
internal class DefaultJwtFactory(private val secretRepository: JwtSecretRepository) : JwtFactory {

    override fun verify(decode: String): DecodedJWT {
        return JWT.require(Algorithm.HMAC256(secretRepository.getJwtSecret()))
            .build()
            .verify(decode)
    }

    override fun decode(decode: String): DecodedJWT {
        return JWT.decode(decode)
    }

    override fun JWTCreator.Builder.sign(): String {
        return sign(Algorithm.HMAC256(secretRepository.getJwtSecret()))
    }
}
