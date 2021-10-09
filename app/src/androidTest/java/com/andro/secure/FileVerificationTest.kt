package com.andro.secure

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.andro.secure.intents.DisplayFileActivity
import org.junit.Assert.assertNotEquals
import org.junit.Assert.fail
import org.junit.Test
import java.io.File

class FileVerificationTest {

    @Test
    fun testThatRequestForFilesIsDeniedIfTryingToDirectoryTraverse() {
        disableDeathOfFileUriExposure()
        launch(MainActivity::class.java).apply {
            onActivity {
                it.createFileWeShouldntFind()

                Thread {
                    launch<DisplayFileActivity>(
                        Intent(Intent.ACTION_SEND).apply {
                            putExtra(
                                Intent.EXTRA_STREAM,
                                Uri.parse("file:///data/data/../data/${it.packageName}/def.txt")
                            )
                        })
                }.start()
            }
        }

        checkWeCantSeePrivateFile()
    }

    @Test
    fun testThatRequestForFilesAreRejectedIfInPrivateFolder() {
        disableDeathOfFileUriExposure()
        launch(MainActivity::class.java).apply {
            onActivity {
                it.createFileWeShouldntFind()

                launchWithIntent(
                    Intent(Intent.ACTION_SEND).apply {
                        putExtra(
                            Intent.EXTRA_STREAM,
                            Uri.parse("file:///data/data/${it.packageName}/def.txt")
                        )
                    })
            }
        }

        checkWeCantSeePrivateFile()
    }


    @Test
    fun testThatRequestForFilesAreAcceptedIfEverythingIsOk() {
        getFileAndStartActivity {
            File(it.filesDir, "abc.txt").apply {
                writeText("Hello world")
            }
        }

        Thread.sleep(1000)
        onView(withId(R.id.testTextView)).check(ViewAssertions.matches(withText("Hello world")))
    }

    @Test
    fun testThatCheckIsNotVulnerableToSymlinkAttachs() {
        disableDeathOfFileUriExposure()
        launch(MainActivity::class.java).apply {
            onActivity {
                it.createFileWeShouldntFind()
                val builder = VmPolicy.Builder()
                StrictMode.setVmPolicy(builder.build())
                // Set readable / writable / executable
                File(it.filesDir, "$FILE").setReadable(true,false);
                File(it.filesDir, "$FILE").setWritable(true,false);
                File(it.filesDir, "$FILE").setExecutable(true,false);

                try {
                    Runtime.getRuntime()
                        .exec("ln -s /data/data/${it.packageName}/def.txt  ${it.filesDir.absolutePath}/$FILE")
                        .waitFor()
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                    fail()
                }

                launchWithIntent(
                    Intent(Intent.ACTION_SEND).apply {
                        putExtra(
                            Intent.EXTRA_STREAM,
                            Uri.parse("file:///${it.filesDir.absolutePath}/$FILE")
                        )
                    })
            }
        }

        checkWeCantSeePrivateFile()
    }


    private fun launchWithIntent(intent: Intent) =
        Thread {
            launch<DisplayFileActivity>(intent)
        }.start()


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
                                FileProvider.getUriForFile(
                                    it,
                                    "com.example.myapp.fileprovider",
                                    createFile(it)
                                )
                            )
                        })
                }.start()
            }
        }
    }

    private fun Context.createFileWeShouldntFind() = File(filesDir, "../def.txt").apply {
        writeText(SHOULD_NOT_SEE)
    }

    private fun checkWeCantSeePrivateFile() {
        Thread.sleep(100)
        onView(withId(R.id.testTextView))
            .check { view, _ ->
                view as TextView
                assertNotEquals(SHOULD_NOT_SEE, view.text.toString())
            }
    }

    companion object {
        const val SHOULD_NOT_SEE = "We should NOT stand for this being visible!"
        const val FILE = "file.txt"
    }

}
