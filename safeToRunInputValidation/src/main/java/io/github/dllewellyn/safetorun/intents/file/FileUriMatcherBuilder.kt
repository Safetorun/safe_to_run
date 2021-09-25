package io.github.dllewellyn.safetorun.intents.file

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
    var allowAnyFile : Boolean

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
    fun addAllowedParentDirectory(parentDirectory : File)
}
