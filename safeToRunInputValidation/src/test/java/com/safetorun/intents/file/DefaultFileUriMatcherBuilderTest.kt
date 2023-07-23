package com.safetorun.intents.file

import android.content.Context
import android.net.Uri
import com.safetorun.intents.utils.assertFalse
import com.safetorun.intents.utils.assertTrue
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.File

@RunWith(RobolectricTestRunner::class)
internal class DefaultFileUriMatcherBuilderTest {

    private val context = mockk<Context>().apply {
        every { packageName } returns PACKAGE
    }

    private val defaultFileUriMatcher = DefaultFileUriBuilderWithVerifier(context)


    @Test
    fun `test that by default we allow a file not in private directory`() {
        File("Abc")
            .verify(context) {}
            .assertTrue()

        Uri.parse("file:///sdcard/Abc")
            .verifyFile(context) {}
            .assertTrue()
    }

    @Test
    fun `test that by default we dont allow a file in private directory`() {
        File("/data/data/$PACKAGE/files/abc.txt")
            .verify(context) {}
            .assertFalse()

        Uri.parse("file:///data/data/$PACKAGE/files/abc.txt")
            .verify(context) {}
            .assertFalse()
    }

    @Test
    fun `test that we are blocked from a file being available if not in the right directory`() {
        Uri.parse("file:///data/data/$PACKAGE/abc.txt")
            .verify(context) {
                File("/data/data/$PACKAGE/files").allowDirectory()
            }
            .assertFalse()
    }


    @Test
    fun `test that by default we dont allow a file in private directory userdata`() {
        File("/user/data/$PACKAGE/files/abc.txt")
            .verify(context) {}
            .assertFalse()

        Uri.parse("file:///user/data/$PACKAGE/files/abc.txt")
            .verify(context) {}
            .assertFalse()
    }

    @Test
    fun `test that when path is null we get a false in response `() {
        Uri.Builder()
            .path(null)
            .build()
            .verify(context) {}
            .assertFalse()
    }


    @Test
    fun `test that we do allow a file in a directory that has been added`() {
        File("/data/data/$PACKAGE/files/abc.txt")
            .verify(context) {
                File("/data/data/$PACKAGE/files/").allowDirectory()
            }
            .assertTrue()
    }

    @Test
    fun `test that we do allow a file in a directory that has been added as an exact match`() {
        File("/data/data/$PACKAGE/files/abc.txt")
            .verifyFile(context) {
                File("/data/data/$PACKAGE/files/abc.txt").allowExactFile()
            }
            .assertTrue()
    }

    @Test
    fun `test that we do not allow a directory in private directory even with directory traversal`() {
        defaultFileUriMatcher
            .verify(File("/data/app/../data/$PACKAGE/files/abc.txt"))
            .assertFalse()
    }

    @Test
    fun `test that we do allow a file in a directory that has been added in a subdir`() {
        defaultFileUriMatcher
            .apply {
                File("/data/data/$PACKAGE/files/").allowDirectory(true)

            }
            .verify(File("/data/data/$PACKAGE/files/def/abc.txt"))
            .assertTrue()
    }

    @Test
    fun `test that we do not allow a file in a directory that has been added in a subdir`() {
        defaultFileUriMatcher
            .apply {
                File("/data/data/$PACKAGE/files/").allowDirectory(false)
            }
            .verify(File("/data/data/$PACKAGE/files/def/abc.txt"))
            .assertFalse()
    }

    @Test
    fun `test that we do allow a file if we allow all`() {
        defaultFileUriMatcher
            .apply {
                allowAnyFile = true
            }
            .verify(File("/data/data/$PACKAGE/files/def/abc.txt"))
            .assertTrue()
    }

    companion object {
        const val PACKAGE = "com.safe.to.run"
    }
}
