package io.github.dllewellyn.safetorun.intents.file

import android.content.Context
import android.net.Uri
import io.github.dllewellyn.safetorun.intents.utils.assertFalse
import io.github.dllewellyn.safetorun.intents.utils.assertTrue
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import java.io.File

internal class DefaultFileUriMatcherBuilderTest : TestCase() {

    private val context = mockk<Context>().apply {
        every { packageName } returns PACKAGE
    }

    private val defaultFileUriMatcher = DefaultFileUriMatcherBuilder(context)

    fun `test that by default we allow a file not in private directory`() {
        defaultFileUriMatcher
            .doesFileCheckPass(File("Abc"))
            .assertTrue()
    }

    fun `test that by default we dont allow a file in private directory`() {
        File("/data/data/$PACKAGE/files/abc.txt")
            .verifyFile(context) {}
            .assertFalse()
    }

    fun `test that by default we dont allow a file in private directory userdata`() {
        File("/user/data/$PACKAGE/files/abc.txt")
            .verifyFile(context) {}
            .assertFalse()
    }

    fun `test that we do allow a file in a directory that has been added`() {
        File("/data/data/$PACKAGE/files/abc.txt")
            .verifyFile(context) {
                addAllowedParentDirectory(
                    FileUriMatcherBuilder.FileUriMatcherCheck(
                        File("/data/data/$PACKAGE/files/"),
                        false
                    )
                )
            }
            .assertTrue()
    }

    fun `test that we do allow a file in a directory that has been added as an exact match`() {
        File("/data/data/$PACKAGE/files/abc.txt")
            .verifyFile(context) {
                addAllowedParentDirectory(
                    FileUriMatcherBuilder.FileUriMatcherCheck(
                        File("/data/data/$PACKAGE/files/abc.txt"),
                        false
                    )
                )
            }
            .assertTrue()
    }


    fun `test that we do not allow a directory in private directory even with directory traversal`() {
        defaultFileUriMatcher
            .doesFileCheckPass(File("/data/app/../data/$PACKAGE/files/abc.txt"))
            .assertFalse()
    }

    fun `test that we do allow a file in a directory that has been added in a subdir`() {
        defaultFileUriMatcher
            .apply {
                addAllowedParentDirectory(
                    FileUriMatcherBuilder.FileUriMatcherCheck(
                        File("/data/data/$PACKAGE/files/"),
                        true
                    )
                )
            }
            .doesFileCheckPass(File("/data/data/$PACKAGE/files/def/abc.txt"))
            .assertTrue()
    }

    fun `test that we do not allow a file in a directory that has been added in a subdir`() {
        defaultFileUriMatcher
            .apply {
                addAllowedParentDirectory(
                    FileUriMatcherBuilder.FileUriMatcherCheck(
                        File("/data/data/$PACKAGE/files/"),
                        false
                    )
                )
            }
            .doesFileCheckPass(File("/data/data/$PACKAGE/files/def/abc.txt"))
            .assertFalse()
    }

    fun `test that we do allow a file if we allow all`() {
        defaultFileUriMatcher
            .apply {
                allowAnyFile = true
            }
            .doesFileCheckPass(File("/data/data/$PACKAGE/files/def/abc.txt"))
            .assertTrue()
    }

    companion object {
        const val PACKAGE = "com.safe.to.run"
    }
}