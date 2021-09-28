package io.github.dllewellyn.safetorun.intents.url

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.intents.utils.assertFalse
import io.github.dllewellyn.safetorun.intents.utils.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class AllowUrlsBuilderImplTest {

    private val urlsBuilder = AllowUrlsBuilderImpl()

    @Test
    fun `test that url check passes if any is set to true`() {
        // Given
        urlsBuilder.allowAnyUrls = true

        // When then
        assertThat(urlsBuilder.doesUrlCheckPass(listOf(URL))).isTrue()
    }

    @Test
    fun `test that url check fails if any is set to false`() {
        // Given
        urlsBuilder.allowAnyUrls = false

        // When then
        assertThat(urlsBuilder.doesUrlCheckPass(listOf(URL))).isFalse()
    }

    @Test
    fun `test that url check fails if a host is not allowed`() {
        // Given
        with(urlsBuilder) {
            urlConfiguration {
                "somethingelse".allowHost()
            }.addConfiguration()
        }

        // When then
        assertThat(urlsBuilder.doesUrlCheckPass(listOf(URL))).isFalse()
    }

    @Test
    fun `test that url check passes if a host is allowed`() {
        // Given
        with(urlsBuilder) {
            urlConfiguration {
                HOST.allowHost()
            }.addConfiguration()
        }

        // When then
        assertThat(urlsBuilder.doesUrlCheckPass(listOf(URL))).isTrue()
    }

    @Test
    fun `test that adding a specific exemption for a URL allows the exemption`() {
        with(urlsBuilder) {
            urlConfiguration {
                URL.allowUrl()
            }.addConfiguration()
        }

        assertThat(urlsBuilder.doesUrlCheckPass(listOf(URL))).isTrue()
    }

    @Test
    fun `test that check fails if there is one allowed URL and one disallowed URL`() {
        listOf(URL, ANOTHER_URL)
            .verifyUrls {
                urlConfiguration {
                    URL.allowUrl()
                }.addConfiguration()
            }.assertFalse()
    }

    @Test
    fun `test that check passes if there is one allowed URL and an irrelevant string`() {
        listOf(URL, "not a url")
            .verifyUrls {
                urlConfiguration {
                    URL.allowUrl()
                }.addConfiguration()
            }.assertTrue()
    }

    @Test
    fun `test that check passes if there is only an irrelevant string `() {
        assertThat(urlsBuilder.doesUrlCheckPass(listOf("not a url"))).isTrue()
    }

    companion object {
        private const val HOST = "abc.com"
        const val URL = "https://$HOST"
        const val ANOTHER_URL = "https://bannedhost.co.uk"
    }
}
