package io.github.dllewellyn.safetorun.backend.repository

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import io.github.dllewellyn.safetorun.backend.configuration.SafeToRunConfigurationDto
import javax.inject.Singleton

@Singleton
internal class DefaultSafeToRunConfigurationRepository : SafeToRunConfigurationRepository {

    private val dbClient = AmazonDynamoDBClientBuilder
        .defaultClient()

    private val mapper by lazy {
        DynamoDBMapper(dbClient)
    }

    override fun retrieveRepositoryForKey(key: String): SafeToRunConfigurationDto {
        return mapper.load(SafeToRunConfigurationDb::class.java, key).safeToRunConfigurationDto
    }

    override fun storeNewConfigurationForKey(key: String, safeToRunConfigurationDto: SafeToRunConfigurationDto) {
        mapper.save(SafeToRunConfigurationDb(key, safeToRunConfigurationDto))
    }

    @DynamoDBTable(tableName = "SafeToRun-Configuration")
    data class SafeToRunConfigurationDb(
        @DynamoDBHashKey(attributeName = "ApiKey")
        val apiKey: String,
        val safeToRunConfigurationDto: SafeToRunConfigurationDto
    )
}
