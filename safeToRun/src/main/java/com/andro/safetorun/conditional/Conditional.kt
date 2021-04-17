package com.andro.safetorun.conditional


data class ConditionalResponse(val failed: Boolean, val message: String? = null)

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

