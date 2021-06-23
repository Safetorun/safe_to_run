package io.github.dllewellyn.safetorun.intents

import android.content.Intent
import android.os.Bundle
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class IntentVerificationBuilderTest {

    private val intent = mockk<Intent>()

    @Before
    fun setUp() {
        every { intent.extras } returns Bundle()
    }

    @Test
    fun `test that intent verifies correctly if there are no containing intents`() {
        assertThat(intent.verify {
            allowAnyUrls = true
        }).isTrue()
    }

    @Test
    fun `test that intent verifies correctly if there are containing intents but you dont care`() {

        // Given
        putDummyIntentIntoExtras()

        // When Then
        assertThat(intent
            .verify {
                allowContainingIntents = true
            }).isTrue()
    }

    @Test
    fun `test that intent verify fails if its embedded`() {
        // Given
        every { intent.extras } returns Bundle().apply {
            putParcelable("Bundle", Bundle().apply {
                putParcelable("AnotherBundle", Bundle().apply {
                    putParcelable("Intent", Intent())
                })
            })
        }

        // When Then
        assertThat(intent
            .verify {
                allowContainingIntents = false
            }).isFalse()
    }

    @Test
    fun `test that intent verify fails if there are containing intents and you do care`() {
        // Given
        putDummyIntentIntoExtras()

        // When Then
        assertThat(intent
            .verify {
                allowContainingIntents = false
            }).isFalse()
    }

    @Test
    fun `test that intent verify fails if there are any urls and urls are banned`() {
        // Given
        every { intent.extras } returns Bundle().apply { putString("url", URL) }

        assertThat(intent.verify { }).isFalse()
    }

    @Test
    fun `test that intent verify passes if there are any urls and urls are not banned`() {
        // Given
        every { intent.extras } returns Bundle().apply { putString("url", URL) }

        assertThat(intent.verify {
            allowAnyUrls = true
        }).isTrue()
    }

    @Test
    fun `test that intent verify passes if there are any urls in a list and urls are not banned`() {
        // Given
        every { intent.extras } returns Bundle().apply { putStringArrayList("url", arrayListOf(URL)) }

        assertThat(intent.verify {
            allowAnyUrls = true
        }).isTrue()
    }

    @Test
    fun `test that intent verify passes if there are any urls in a array list and urls are not banned`() {
        // Given
        every { intent.extras } returns Bundle().apply { putStringArray("url", arrayOf(URL)) }

        assertThat(intent.verify {
            allowAnyUrls = true
        }).isTrue()
    }

    private fun putDummyIntentIntoExtras() {
        every { intent.extras } returns Bundle().apply { putParcelable("Intent", Intent()) }
    }

    companion object {
        const val URL = "https://abc.com"
    }
}
