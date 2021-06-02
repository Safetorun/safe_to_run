package io.github.dllewellyn.safetorun.api

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import javax.net.ssl.HttpsURLConnection

internal class DefaultHttpClient(private val url: String) : SafeToRunHttpClient {

    override fun <T, O> post(
        path: String,
        headers: Map<String, String>,
        body: T,
        serializer: SerializationStrategy<T>,
        deserializer: DeserializationStrategy<O>
    ): O {
        val urlConnection = buildConnection(path, headers)

        urlConnection.outputStream.write(Json.encodeToString(serializer, body).toByteArray())
        urlConnection.outputStream.flush()

        urlConnection.checkStatusAndThrowException()
        val content = urlConnection.readContent()
        urlConnection.disconnect()
        return Json.decodeFromString(deserializer, content.toString())
    }

    private fun HttpURLConnection.readContent(): StringBuilder {
        val reader = BufferedReader(inputStream.reader())
        val content = StringBuilder()
        reader.use { bufferedReader ->
            var line = bufferedReader.readLine()
            while (line != null) {
                content.append(line)
                line = bufferedReader.readLine()
            }
        }
        return content
    }

    private fun buildConnection(path: String, headers: Map<String, String>): HttpURLConnection {
        val connection = URL("$url/${path.removePrefix("/")}").openConnection()
        val urlConnection = connection.connectionForUrl(url)

        urlConnection.doInput = true
        urlConnection.doOutput = true
        urlConnection.requestMethod = "POST"
        urlConnection.instanceFollowRedirects = true
        urlConnection.addRequestProperty("accept", ACCEPT_RESPONSE)
        urlConnection.addRequestProperty("Content-Type", CONTENT_TYPE)
        headers.forEach {
            urlConnection.addRequestProperty(it.key, it.value)
        }

        return urlConnection
    }

    @Throws(ApiException::class)
    private fun HttpURLConnection.checkStatusAndThrowException() {
        if (responseCode !in LOWER_ACCEPTABLE_RESPONSE_CODE..UPPER_ACCEPTABLE_RESPONSE_CODE) {
            throw ApiException("Failed with code $responseCode")
        }
    }

    companion object {
        private const val LOWER_ACCEPTABLE_RESPONSE_CODE = 200
        private const val UPPER_ACCEPTABLE_RESPONSE_CODE = 299
        private const val CONTENT_TYPE = "application/json"
        private const val ACCEPT_RESPONSE = "application/json"
    }
}

fun URLConnection.connectionForUrl(url: String) = if (url.startsWith("http://")) {
    this as HttpURLConnection
} else {
    this as HttpsURLConnection
}
