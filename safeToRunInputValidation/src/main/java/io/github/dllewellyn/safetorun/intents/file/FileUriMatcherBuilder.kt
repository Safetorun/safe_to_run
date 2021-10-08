package io.github.dllewellyn.safetorun.intents.file

import android.net.Uri
import java.io.File

/**
 * The builder for allowing File URIs to pass the verifiation service
 */
interface FileUriMatcherBuilder {

    /**
     * Whether or not you want to allow any files to pas through verification.
     *
     * WARNING: Don't do this, it will allow things like file:///data/data/com.your.app/databases to
     * be read
     */
    var allowAnyFile: Boolean

    /**
     * Allow a file URI to belong to a particular parent directory.
     *
     * Example:
     *
     * ```addAllowedParentDirectory(File("/data/data/com.my.app/files")```
     *
     * Will fail for
     *
     * /data/data/com.my.app/databases
     */
    fun addAllowedParentDirectory(parentDirectory: FileUriMatcherCheck)

    /**
     * Allow a file to be opened
     *
     * @param file the file you want to allow
     */
    fun addAllowedExactFile(file : File)

    /**
     * Allow a file to be opened
     *
     * @receiver the file you want to allow
     */
    fun File.allowExactFile()

    /**
     * @see addAllowedParentDirectory
     */
    fun FileUriMatcherCheck.allowParentDir()

    /**
     * Does file check pass
     *
     * @param file file to check if we should 'open' it
     *
     * @return if file check passes
     */
    fun doesFileCheckPass(file: File): Boolean

    /**
     * Does file check pass
     *
     * @param uri file to check if we should 'open' it
     *
     * @return if file check passes
     */
    fun doesFileCheckPass(uri: Uri): Boolean

    /**
     * Convert a file to a allow directory check
     *
     * @param allowSubdirectories whether or not to allow subdirectories
     *
     * @receiver File the file to allow
     */
    fun File.allowDirectory(allowSubdirectories: Boolean = false) =
        FileUriMatcherCheck(this, allowSubdirectories)
            .allowParentDir()

    /**
     * Convert a file to allow directory check
     *
     * @receiver File the file to allow
     */
    fun File.allowDirectoryAndSubdirectories() =
        allowDirectory(true)

    /**
     * Create a check for URI matcher
     *
     * @param directoryToAllow the directory to allow
     * @param allowSubdirectories allow subdirectories or not
     */
    data class FileUriMatcherCheck(val directoryToAllow: File, val allowSubdirectories: Boolean)
}



