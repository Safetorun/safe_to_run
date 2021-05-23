package io.github.dllewellyn.safetorun.backend.repository

import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest
import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException
import com.amazonaws.util.Base64
import org.slf4j.LoggerFactory
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.inject.Singleton

@Singleton
class AwsSecretsManagerJwtSecretRepository : JwtSecretRepository {
    private val awsSecretsManager by lazy { AWSSecretsManagerClientBuilder.defaultClient() }
    private val keySpec = KeyFactory.getInstance("RSA")

    override fun retrieveJwtPublicKey(): RSAPublicKey {
        return try {
            awsSecretsManager.getSecretValue(GetSecretValueRequest().withSecretId(RSA_PUBLIC_KEY))
                .secretString
                .run(Base64::decode)
                .run { X509EncodedKeySpec(this) }
                .run { keySpec.generatePublic(this) as RSAPublicKey }
        } catch (e: ResourceNotFoundException) {
            Logger.info("Resource not found", e)
            throw e
        }
    }

    override fun retrieveJwtPrivateKey(): RSAPrivateKey {
        return try {
            awsSecretsManager.getSecretValue(GetSecretValueRequest().withSecretId(RSA_PRIVATE_KEY))
                .secretString
                .run(Base64::decode)
                .run { PKCS8EncodedKeySpec(this) }
                .run { keySpec.generatePrivate(this) }
        } catch (e: ResourceNotFoundException) {
            Logger.info("Resource not found", e)
            throw e
        } as RSAPrivateKey
    }

    companion object {
        const val RSA_PUBLIC_KEY = "PublicKeySafeToRun"
        const val RSA_PRIVATE_KEY = "PrivateKeySafeToRun"
        private val Logger = LoggerFactory.getLogger(AwsSecretsManagerJwtSecretRepository::class.java)
    }
}
