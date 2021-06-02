package io.github.dllewellyn.safetorun.features.oscheck

import io.github.dllewellyn.safetorun.conditional.ConditionalResponse

internal fun ConditionalResponse.messageIfFailed(message: String) = if (failed) {
    copy(message = message)
} else {
    this
}
