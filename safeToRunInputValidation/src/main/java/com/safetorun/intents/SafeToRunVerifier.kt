package com.safetorun.intents

/**
 * Base class which allows for verification of an input or output
 */
interface SafeToRunVerifier<T> {

    /**
     * Add post-action
     */
    fun andThen(next: (Boolean, T) -> Unit): SafeToRunVerifier<T>

    /**
     * Verify the input
     *
     * @param input the input to verify
     *
     * @return true if the input is safe to use
     */
    fun verify(input: T): Boolean

}


