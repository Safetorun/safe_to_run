package io.github.dllewellyn.safetorun.backend.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import io.github.dllewellyn.safetorun.backend.repository.JwtSecretRepository
import javax.inject.Singleton

@Singleton
class DefaultJwtFactory(private val jwtSecretRepository: JwtSecretRepository) : JwtFactory {

    private val algorithm by lazy {
        Algorithm.RSA256(jwtSecretRepository.retrieveJwtPublicKey(), jwtSecretRepository.retrieveJwtPrivateKey())
    }

    override fun verify(decode: String): DecodedJWT {
        return JWT.require(algorithm)
            .build()
            .verify(decode)
    }

    override fun decode(decode: String): DecodedJWT {
        return JWT.decode(decode)
    }

    override fun JWTCreator.Builder.sign(): String {
        return sign(algorithm)
    }
}
