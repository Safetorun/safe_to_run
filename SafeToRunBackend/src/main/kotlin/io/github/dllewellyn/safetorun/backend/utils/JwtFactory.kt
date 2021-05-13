package io.github.dllewellyn.safetorun.backend.utils

import com.auth0.jwt.JWTCreator
import com.auth0.jwt.interfaces.DecodedJWT

interface JwtFactory {
    fun verify(decode: String): DecodedJWT
    fun decode(decode: String): DecodedJWT
    fun JWTCreator.Builder.sign(): String
}
