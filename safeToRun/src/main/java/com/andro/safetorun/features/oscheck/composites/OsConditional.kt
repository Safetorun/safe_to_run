package com.andro.safetorun.features.oscheck.composites

fun interface OsConditional {
    operator fun invoke(): Boolean
}

fun osConditionalBuilder(builder: OsConditionalBuilder.() -> Unit): OsConditional {
    return with(OsConditionalBuilder()) {
        builder()
        build()
    }
}

