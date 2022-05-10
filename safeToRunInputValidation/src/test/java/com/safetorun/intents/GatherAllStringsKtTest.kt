package com.safetorun.intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.common.truth.Truth.assertThat
import com.safetorun.intents.utils.assertContains
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class GatherAllStringsKtTest {

    @Test
    fun `test that a null bundle returns an empty list`() {
        assertThat((null as Bundle?).gatherAllStrings()).isEmpty()
    }

    @Test
    fun `test that an empty intent returns an empty list`() {
        assertThat((null as Intent?).gatherAllStrings()).isEmpty()
    }

    @Test
    fun `test that gathering all strings from intent ignores the extras if there arent any`() {
        // Given

        val uri = Uri.parse("https://abc.com")

        // When
        val intent = Intent().apply {
            data = uri
        }

        intent.gatherAllStrings().assertContains(uri.toString())

    }

    @Test
    fun `test that gathering all strings from intent gathers the extras`() {
        // Given
        val key = "samplekey"
        val uri = Uri.parse("https://abc.com")

        // When
        val bundle = Bundle().apply {
            putAll(
                Bundle().apply {
                    putString("", key)
                }
            )
        }

        val intent = Intent().apply {
            putExtra("bundle", bundle)
            data = uri
        }

        intent.gatherAllStrings().assertContains(key, uri.toString())

    }

    @Test
    fun `test that nested bundles call will gather all strings`() {
        val key = "samplekey"

        Bundle().apply {
            putAll(
                Bundle().apply {
                    putString("", key)
                }
            )
        }.gatherAllStrings().assertContains(key)
    }

    @Test
    fun `test that serializable URI is found`() {
        // Given
        val serializableUri = Uri.parse("https://google.co.uk")

        // When
        val result = Bundle().apply {
            putParcelable("uri", serializableUri)
        }.gatherAllStrings()

        // Then
        assertThat(result).contains(serializableUri.toString())
    }

    @Test
    fun `test that list of uris is found`() {
        // Given
        val firstList = arrayListOf(
            Uri.parse("https://amazon.co.uk"),
            Uri.parse("https://google.com")
        )

        // when
        val result = Bundle().apply {
            putParcelableArrayList("first", firstList)
        }.gatherAllStrings()

        // Then
        assertThat(result).apply {
            firstList.forEach {
                contains(it.toString())
            }
        }
    }

    @Test
    fun `test that list of strings is found`() {
        // Given
        val firstList = arrayListOf("Abc", "Hij")
        val secondList = arrayListOf("Def", "Klm")

        val expected = mutableListOf<String>().apply {
            addAll(firstList)
            addAll(secondList)
        }

        // when
        val result = Bundle().apply {
            putStringArrayList("first", firstList)
            putStringArrayList("second", secondList)
        }.gatherAllStrings()

        // Then
        assertThat(result).apply {
            expected.forEach {
                contains(it)
            }
        }
    }
}
