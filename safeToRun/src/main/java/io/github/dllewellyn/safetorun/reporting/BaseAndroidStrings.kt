package io.github.dllewellyn.safetorun.reporting

import android.content.Context
import android.content.res.Resources

internal open class BaseAndroidStrings(private val context: Context) {

    protected val resources: Resources
        get() = context.resources
}
