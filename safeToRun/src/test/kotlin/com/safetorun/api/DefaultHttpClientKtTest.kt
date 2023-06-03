package com.safetorun.api

import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import junit.framework.TestCase
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

internal class DefaultHttpClientKtTest : TestCase() {

    private val connectionHttps = mockk<HttpsURLConnection>()
    private val connectionHttp = mockk<HttpURLConnection>()

    fun `test that when we retrieve connection for url we get the right type of URL Connection`() {
        assertThat(connectionHttp.connectionForUrl("http://blah.com")).isInstanceOf(HttpURLConnection::class.java)
        assertThat(connectionHttps.connectionForUrl("https://blah.com")).isInstanceOf(HttpsURLConnection::class.java)
    }
}
