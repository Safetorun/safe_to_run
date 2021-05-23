package io.github.dllewellyn.safetorun.backend.util

import io.github.dllewellyn.safetorun.backend.repository.JwtSecretRepository
import io.micronaut.context.annotation.Primary
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import javax.inject.Singleton

@Primary
@Singleton
class DummyJwtSecretRepository : JwtSecretRepository {

    private val rsaGenerator by lazy { KeyPairGenerator.getInstance("RSA") }
    private val keyPair by lazy { rsaGenerator.genKeyPair() }

    override fun retrieveJwtPublicKey(): RSAPublicKey {
        return keyPair.public as RSAPublicKey
    }

    override fun retrieveJwtPrivateKey(): RSAPrivateKey {
        return keyPair.private as RSAPrivateKey
    }
}
