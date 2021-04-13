package com.andro.safetorun.conditional

fun interface Conditional {
    /**
     * Returns true if it's pass
     */
    operator fun invoke(): Boolean
}

internal fun conditionalBuilder(builder: ConditionalBuilder.() -> Unit): Conditional {
    return with(ConditionalBuilder()) {
        builder()
        build()
    }
}

