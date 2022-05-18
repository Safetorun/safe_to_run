package com.safetorun.intents.file

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import java.io.File

internal class DefaultFileUriMatcherBuilder(private val context: Context) : FileUriMatcherBuilder {

    private val allowedDirectories = mutableListOf<FileUriMatcherBuilder.FileUriMatcherCheck>()
    private val allowExactFile = mutableListOf<File>()

    override var allowAnyFile: Boolean = false

    override fun addAllowedParentDirectory(parentDirectory: FileUriMatcherBuilder.FileUriMatcherCheck) {
        allowedDirectories.add(parentDirectory)
    }

    override fun addAllowedExactFile(file: File) {
        allowExactFile.add(file)
    }

    override fun File.allowExactFile() {
        addAllowedExactFile(this)
    }

    override fun FileUriMatcherBuilder.FileUriMatcherCheck.allowParentDir() {
        addAllowedParentDirectory(this)

    }

    override fun doesFileCheckPass(file: File): Boolean {
        return isDirectoryPublicDirectory(file) ||
                allowAnyFile ||
                isExactFileAllowed(file) ||
                isDirectoryAllowed(file) ||
                allowedDirectories
                    .filter { it.allowSubdirectories }
                    .firstOrNull { recursivelyCheckDirectory(it.directoryToAllow, file) } != null
    }

    private fun isDirectoryAllowed(file: File) = allowedDirectories.firstOrNull {
        it.directoryToAllow.canonicalPath == file.canonicalFile.parent?.let { parentDirectoryPath ->
            File(
                parentDirectoryPath
            ).canonicalPath
        }
    } != null

    private fun isExactFileAllowed(file: File) = allowExactFile.firstOrNull {
        it.canonicalPath == file.canonicalPath
    } != null

    private fun isDirectoryPublicDirectory(file: File) = !file.isPrivateDirectory(context)

    override fun doesFileCheckPass(uri: Uri): Boolean {
        return uri.path?.let { doesFileCheckPass(File(it)) } ?: false
    }

    private fun recursivelyCheckDirectory(parent: File, fileWereLookingFor: File): Boolean =
        fileWereLookingFor.canonicalPath.startsWith(parent.canonicalPath)
}

@SuppressLint("SdCardPath")
private fun File.isPrivateDirectory(context: Context) =
    canonicalPath.contains("/data/data/${context.packageName}") ||
            canonicalPath.contains("/user/data/${context.packageName}")

typealias FileVerifier = FileUriMatcherBuilder.() -> Unit

/**
 * Verify a file to see if it can safely be opened
 *
 * @param context android context
 * @param config the configuration to use to verify this file
 *
 * @return true if the check passes
 */
fun File.verifyFile(context: Context, config: FileVerifier) =
    DefaultFileUriMatcherBuilder(context)
        .apply(config)
        .doesFileCheckPass(this)


/**
 * Verify a file to see if it can safely be opened
 *
 * @param context android context
 * @param config the configuration to use to verify this file
 *
 * @return true if the check passes
 */
fun Uri.verifyFile(context: Context, config: FileVerifier) =
    DefaultFileUriMatcherBuilder(context)
        .apply(config)
        .doesFileCheckPass(this)
