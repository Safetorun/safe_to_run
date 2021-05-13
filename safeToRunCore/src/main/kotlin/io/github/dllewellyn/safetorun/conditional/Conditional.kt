package io.github.dllewellyn.safetorun.conditional

fun interface Conditional {
    /**
     * Returns true if it's pass
     */
    operator fun invoke(): ConditionalResponse
}

fun conditionalBuilder(builder: ConditionalBuilder.() -> Unit): Conditional {
    return with(ConditionalBuilder()) {
        builder()
        build()
    }
}
