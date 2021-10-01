package com.andro.secure

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.StrictMode
import androidx.core.content.FileProvider
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.andro.secure.intents.DisplayFileActivity
import com.andro.secure.util.waitForView
import org.junit.Test
import java.io.File

class FileVerificationTest {

    @Test
    fun testThatRequestForFilesAreAcceptedIfEverythingIsOk() {
        disableDeathOfFileUriExposure()
        launch(MainActivity::class.java).apply {
            onActivity {
                File(it.filesDir, "../def.txt").apply {
                    writeText("We should NOT stand for this being visible!")
                }

                Thread {
                    launch<DisplayFileActivity>(
                        Intent(Intent.ACTION_SEND).apply {
                            putExtra(
                                Intent.EXTRA_STREAM,
                                Uri.parse("file:///data/data/${it.packageName}/def.txt")
                            )
                        })
                }.start()
            }
        }

        Thread.sleep(1000)
        onView(withId(R.id.testTextView)).check(ViewAssertions.matches(withText("We should NOT stand for this being visible!")))
    }

    @Test
    fun testThatRequestForFilesAreRejectedIfInPrivateFolder() {
        getFileAndStartActivity {
            File(it.filesDir, "abc.txt").apply {
                writeText("Hello world")
            }
        }

        Thread.sleep(1000)
        onView(withId(R.id.testTextView)).check(ViewAssertions.matches(withText("Hello world")))
    }

    private fun disableDeathOfFileUriExposure() {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                val m = StrictMode::class.java.getMethod("disableDeathOnFileUriExposure")
                m.invoke(null)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getFileAndStartActivity(createFile: (Context) -> File) {
        launch(MainActivity::class.java).apply {
            onActivity {
                Thread {
                    launch<DisplayFileActivity>(
                        Intent(Intent.ACTION_SEND).apply {
                            putExtra(
                                Intent.EXTRA_STREAM,
                                FileProvider.getUriForFile(it, "com.example.myapp.fileprovider", createFile(it))
                            )
                        })
                }.start()
            }
        }
    }

}
