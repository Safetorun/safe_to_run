package io.github.dllewellyn.safetorun

import java.io.BufferedReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

fun main() {
    val bra: ByteArray = ("{" +
            "  \"apiKey\": \"Apikey\"," +
            "  \"deviceId\": \"deviceId\"," +
            "  \"osCheck\": {" +
            "    \"osVersion\": \"31\"," +
            "    \"manufacturer\": \"manufacturer\"" +
            "  }," +
            "  \"installOrigin\": {" +
            "    \"installOriginPackageName\": \"instll origin\"" +
            "  }," +
            "  \"blacklistedApp\": {" +
            "    \"installedPackages\": [" +
            "      \"com.a.b\"," +
            "      \"com.d.e\"" +
            "    ]" +
            "  }," +
            "  \"signatureVerification\": {" +
            "    \"signatureVerification\": \"signature\"" +
            "  }" +
            "}").toByteArray()
    val urlConnection =
        (URL("https://rygl69bpz0.execute-api.eu-west-1.amazonaws.com/Prod/deviceCheck")
            .openConnection() as HttpsURLConnection)

    println("Url connection")

    urlConnection.doInput = true
    urlConnection.doOutput = true
    urlConnection.requestMethod = "POST"
    urlConnection.instanceFollowRedirects = true
    urlConnection.addRequestProperty("accept", "application/json")
    urlConnection.addRequestProperty("Content-Type", "application/json")
    urlConnection.addRequestProperty("x-api-key", "5bzdwZ8Drs1AIsmJAx0M37bndOeEkwbv6pI5fjx1")
    println("API key set")
    urlConnection.outputStream.write(bra)
    urlConnection.outputStream.flush()

    val reader = BufferedReader(urlConnection.inputStream.reader())
    val content = StringBuilder()
    reader.use { reader ->
        var line = reader.readLine()
        while (line != null) {
            content.append(line)
            line = reader.readLine()
        }
    }

    println(content)

    urlConnection.disconnect()
}
