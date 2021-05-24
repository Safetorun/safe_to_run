package io.github.dllewellyn.safetorun.backend.repository

import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import javax.inject.Singleton

@Singleton
class AWSSecretsManagerJwtSecretRepository : JwtSecretRepository {

    private val awsSecretsManager by lazy { AWSSecretsManagerClientBuilder.defaultClient() }
    private val objectMapper = ObjectMapper()

    override fun getJwtSecret(): String {
        return objectMapper.readValue(
            awsSecretsManager
                .getSecretValue(GetSecretValueRequest().withSecretId(SECRET_ID))
                .secretString,
            JwtSecrets::class.java
        ).jwtSecret
    }

    private data class JwtSecrets(@JsonProperty("JwtSecret") val jwtSecret: String)

    companion object {
        const val SECRET_ID = "JwtSecret"
    }
}
