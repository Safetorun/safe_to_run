package io.github.dllewellyn.safetorun.intents.url

import com.google.common.truth.Truth.assertThat
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

    companion object {
        private const val HOST = "abc.com"
        const val URL = "https://$HOST"
    }
}
