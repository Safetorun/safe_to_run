package com.safetorun.intents.file

import android.content.Context
import android.net.Uri
import java.io.File


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
        .verify(this)


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
        .run { path?.let { verify(File(it)) } ?: false }
