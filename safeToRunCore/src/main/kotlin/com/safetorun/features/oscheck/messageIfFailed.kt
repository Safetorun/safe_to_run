package com.safetorun.features.oscheck

import com.safetorun.conditional.ConditionalResponse

/**
 * Message to display if response is a failure
 *
 * @param message the message to display on failure
 */
fun ConditionalResponse.messageIfFailed(message: String) = if (failed) {
    copy(message = message)
} else {
    this
}
