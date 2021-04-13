package com.andro.safetorun.conditional

fun interface Conditional {
    operator fun invoke(): Boolean
}

internal fun conditionalBuilder(builder: ConditionalBuilder.() -> Unit): Conditional {
    return with(ConditionalBuilder()) {
        builder()
        build()
    }
}

