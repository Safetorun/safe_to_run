package io.github.dllewellyn.safetorun.backend.utils

import com.auth0.jwt.JWTCreator
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT

/**
 * Factory to use to decode a JWT
 */
internal interface JwtFactory {
    /**
     * Decode a JWT token
     *
     * @param decode the string representation of a JWT string
     *
     * @return returns a decoded JWT string.
     *
     * @throws SignatureVerificationException if the signature verification fails
     * @throws TokenExpiredException if the token has expired
     */
    fun verify(decode: String): DecodedJWT

    /**
     * Decode a JWT token without verifying. Different to [verify] in that
     * it does not verify the token
     *
     * @param decode the JWT token to decode
     *
     * @return a decoded JWT
     */
    fun decode(decode: String): DecodedJWT

    /**
     * Sign a token
     *
     * @return a JWT that has been signed
     */
    fun JWTCreator.Builder.sign(): String
}
