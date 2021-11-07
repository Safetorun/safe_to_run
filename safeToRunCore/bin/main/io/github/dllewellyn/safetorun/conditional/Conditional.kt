package io.github.dllewellyn.safetorun.conditional

/**
 * Conditional for failing an OS Check
 */
fun interface Conditional {
    /**
     * Returns true if it's pass
     */
    operator fun invoke(): ConditionalResponse
}

/**
 * Build a conditional OS Check
 *
 * @param builder block for a builder
 *
 * @return a conditional
 */
fun conditionalBuilder(builder: ConditionalBuilder.() -> Unit): Conditional {
    return with(ConditionalBuilder()) {
        builder()
        build()
    }
}
