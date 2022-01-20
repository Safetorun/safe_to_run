package io.github.dllewellyn.safetorun.pinscreen

import android.util.Base64
import java.security.SecureRandom

const val SALT_SIZE = 20

internal fun secureRandomString() = SecureRandom().run {
    val bytes = ByteArray(SALT_SIZE)
    nextBytes(bytes)
    Base64.encodeToString(bytes, 0)
}
