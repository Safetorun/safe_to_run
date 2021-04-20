package com.andro.safetorun.reporting

import android.content.Context
import android.content.res.Resources

open class BaseAndroidStrings(private val context: Context) {

    protected val resources: Resources
        get() = context.resources

}