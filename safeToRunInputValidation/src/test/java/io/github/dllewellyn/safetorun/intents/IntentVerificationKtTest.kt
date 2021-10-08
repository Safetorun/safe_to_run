package io.github.dllewellyn.safetorun.intents

import android.content.Intent
import android.os.Bundle
import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.intents.utils.assertContains
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class IntentVerificationKtTest  {

    @Test
    fun `test that intent verification gathers all nested intents`() {

        val insideIntent = Intent()
        val insideBundle = Intent()
        val nestedInBundle = Intent()

        val doubleInnerBundle = Bundle().apply {
            putParcelable("nestedInBundle", nestedInBundle)
        }

        val innerBundle = Bundle().apply {
            putAll(doubleInnerBundle)
            putParcelable("insideBundle", insideBundle)
        }

        Bundle()
            .apply {
                putAll(innerBundle)
                putParcelable("intent", insideIntent)
            }
            .gatherAllIntents()
            .assertContains(nestedInBundle, insideIntent, insideBundle)
    }
}

