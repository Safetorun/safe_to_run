package com.safetorun.api

import com.google.common.truth.Truth.assertThat
import com.safetorun.models.models.DataWrappedSignatureResult
import com.safetorun.models.models.DeviceSignatureDto
import junit.framework.TestCase
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.assertThrows
import org.mockserver.integration.ClientAndServer
import org.mockserver.model.HttpRequest.request
import org.mockserver.model.HttpResponse.response
import kotlin.random.Random

internal class DefaultISafeToRunApiIntegrationTest : TestCase() {

    private var port: Int = 0
    private var mockServer: ClientAndServer = ClientAndServer.startClientAndServer(port)
    private var url: String = ""
    private val response = DataWrappedSignatureResult(DeviceSignatureDto("signature"))

    override fun setUp() {
        port = Random.nextInt(9000, 9999)
        url = "http://localhost:$port"
        mockServer = ClientAndServer.startClientAndServer(port)
    }

    override fun tearDown() {
        mockServer.close()
    }

    fun `test that response is expected for integration`() {
        mockServer.`when`(
            request()
                .withHeader("x-api-key", API_KEY)
                .withBody(Json.encodeToString(deviceInformation))
        ).respond(
            response()
                .withStatusCode(200)
                .withBody(Json.encodeToString(response))
        )

        val httpClient = DefaultSafeToRunApi(DefaultHttpClient(url), API_KEY)

        assertThat(httpClient.postNewDevice(deviceInformation)).isEqualTo(response.data)
    }

    fun `test that response exception happens if there is a wrong status code`() {
        mockServer.`when`(
            request()
                .withHeader("x-api-key", API_KEY)
                .withBody(Json.encodeToString(deviceInformation))
        ).respond(
            response()
                .withStatusCode(404)
        )

        val httpClient = DefaultSafeToRunApi(DefaultHttpClient(url), API_KEY)

        assertThrows<ApiException> { httpClient.postNewDevice(deviceInformation) }
    }

    companion object {

        private const val API_KEY = "ApIkEy"
    }
}
