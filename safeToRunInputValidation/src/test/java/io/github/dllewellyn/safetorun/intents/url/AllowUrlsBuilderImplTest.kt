package io.github.dllewellyn.safetorun.intents.url

import android.content.Intent
import android.os.Bundle
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class AllowUrlsBuilderImplTest {

    private val urlsBuilder = AllowUrlsBuilderImpl()
    private val intent = mockk<Intent>()

    @Test
    fun `test that url check passes if any is set to true`() {
        // Given
        urlsBuilder.allowAnyUrls = true
        every { intent.extras } returns Bundle().apply {
            putString(URL_KEY, URL)
        }

        // When then
        assertThat(urlsBuilder.doesUrlCheckPass(intent)).isTrue()
    }

    @Test
    fun `test that url check fails if any is set to false`() {
        // Given
        urlsBuilder.allowAnyUrls = false

        every { intent.extras } returns Bundle().apply {
            putString(URL_KEY, URL)
        }

        // When then
        assertThat(urlsBuilder.doesUrlCheckPass(intent)).isFalse()
    }

    @Test
    fun `test that url check fails if a host is not allowed`() {
        // Given
        with(urlsBuilder) {
            "somethingelse".allowHost()
        }
        every { intent.extras } returns Bundle().apply {
            putString(URL_KEY, URL)
        }

        // When then
        assertThat(urlsBuilder.doesUrlCheckPass(intent)).isFalse()
    }

    @Test
    fun `test that url check passes if a host is allowed`() {
        // Given
        with(urlsBuilder) {
            HOST.allowHost()
        }

        every { intent.extras } returns Bundle().apply {
            putString(URL_KEY, URL)
        }

        // When then
        assertThat(urlsBuilder.doesUrlCheckPass(intent)).isTrue()
    }

    @Test
    fun `test that adding a specific exemption for a URL allows the exemption`() {
        with(urlsBuilder) {
            URL.allowUrl()
        }

        every { intent.extras } returns Bundle().apply {
            putString(URL_KEY, URL)
        }

        assertThat(urlsBuilder.doesUrlCheckPass(intent)).isTrue()
    }

    companion object {
        private const val HOST = "abc.com"
        const val URL = "https://$HOST"
        const val URL_KEY = "url"
    }
}
