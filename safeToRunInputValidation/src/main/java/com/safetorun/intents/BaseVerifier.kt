package com.safetorun.intents

/**
 * Base class which allows for verification of an input or output and allows adding additional actions when implemented
 */
internal abstract class BaseVerifier<T> : SafeToRunVerifier<T> {

    private var nextList: MutableList<(Boolean, T) -> Boolean> = mutableListOf()

    override fun andThen(next: (Boolean, T) -> Boolean): SafeToRunVerifier<T> {
        nextList.add(next)
        return this
    }

    abstract fun internalVerify(input: T): Boolean

    override fun verify(input: T): Boolean {
        var result = internalVerify(input)
        for (nextFunction in nextList) {
            result = nextFunction(result, input)
            if (!result) {
                break
            }
        }
        return result
    }
}
