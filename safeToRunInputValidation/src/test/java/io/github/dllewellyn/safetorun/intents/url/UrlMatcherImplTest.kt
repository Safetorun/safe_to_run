package io.github.dllewellyn.safetorun.intents.url

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class UrlMatcherImplTest {

    private val urlMatcherImpl = UrlMatcherImpl()

    @Test
    fun `test that the url matcher matches a http request`() {
        assertThat(urlMatcherImpl.isUrl("http://abc.com")).isTrue()
    }

    @Test
    fun `test that the url matcher matches a https request`() {
        assertThat(urlMatcherImpl.isUrl("https://abc.com")).isTrue()
    }

    @Test
    fun `test that the url matcher matches an at symbol request`() {
        assertThat(urlMatcherImpl.isUrl("http://abc.com@def.com")).isTrue()
    }

    @Test
    fun `test that the url matcher does not match random string `() {
        assertThat(urlMatcherImpl.isUrl("Daniel Llewellyn")).isFalse()
    }
}
