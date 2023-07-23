package com.safetorun.intents.file

import android.annotation.SuppressLint
import android.content.Context
import com.safetorun.intents.BaseVerifier
import java.io.File


/**
 * Interface for building an allows URL but with a verifier
 */
internal class DefaultFileUriBuilderWithVerifier(
    context : Context
) : FileUriMatcherWithVerifier,
    FileUriMatcherBuilder by DefaultFileUriMatcherBuilder(context),
    BaseVerifier<File>() {
    override fun internalVerify(input: File): Boolean {
        return doesFileCheckPass(input)
    }
}

internal class DefaultFileUriMatcherBuilder(private val context: Context) :
    FileUriMatcherBuilder {

    private val allowedDirectories = mutableListOf<FileUriMatcherCheck>()
    private val allowExactFile = mutableListOf<File>()

    override var allowAnyFile: Boolean = false

    private fun addAllowedParentDirectory(parentDirectory: FileUriMatcherCheck) {
        allowedDirectories.add(parentDirectory)
    }

    override fun addAllowedExactFile(file: File) {
        allowExactFile.add(file)
    }

    override fun File.allowExactFile() {
        addAllowedExactFile(this)
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

    override fun File.allowDirectory(allowSubdirectories: Boolean) {
        addAllowedParentDirectory(FileUriMatcherCheck(this, allowSubdirectories))
    }
}

private fun recursivelyCheckDirectory(parent: File, fileWereLookingFor: File): Boolean =
    fileWereLookingFor.canonicalPath.startsWith(parent.canonicalPath)


@SuppressLint("SdCardPath")
private fun File.isPrivateDirectory(context: Context) =
    canonicalPath.contains("/data/data/${context.packageName}") ||
            canonicalPath.contains("/user/data/${context.packageName}")

