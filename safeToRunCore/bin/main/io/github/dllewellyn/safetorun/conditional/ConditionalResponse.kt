package io.github.dllewellyn.safetorun.conditional

/**
 * A conditional response
 *
 * @param failed does this response indicate a failure?
 * @param message the message to display for this failure (or null if you want to display
 *  a generic message)
 */
data class ConditionalResponse(val failed: Boolean, val message: String? = null)
