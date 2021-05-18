package io.github.dllewellyn.safetorun.features.oscheck

import android.os.Build

internal class OSInformationQueryAndroid : OSInformationQuery {
    override fun osVersion(): Int = Build.VERSION.SDK_INT
    override fun manufacturer(): String = Build.MANUFACTURER
    override fun model(): String = Build.MODEL
}
