package io.github.dllewellyn.safetorun.backend.repository

import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

interface JwtSecretRepository {
    fun retrieveJwtPublicKey(): RSAPublicKey
    fun retrieveJwtPrivateKey(): RSAPrivateKey
}
