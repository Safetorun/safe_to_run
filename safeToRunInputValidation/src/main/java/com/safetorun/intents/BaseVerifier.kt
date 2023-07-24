package com.safetorun.intents

/**
 * Base class which allows for verification of an input or output and allows adding additional actions when implemented
 */
internal abstract class BaseVerifier<T> : SafeToRunVerifier<T> {

    private var nextList: MutableList<(Boolean, T) -> Unit> = mutableListOf()

    override fun andThen(next: (Boolean, T) -> Unit): SafeToRunVerifier<T> {
        nextList.add(next)
        return this
    }

    abstract fun internalVerify(input: T): Boolean

    override fun verify(input: T): Boolean {
        val result = internalVerify(input)
        nextList.forEach {
            it.invoke(result, input)
        }
        return result
    }
}
