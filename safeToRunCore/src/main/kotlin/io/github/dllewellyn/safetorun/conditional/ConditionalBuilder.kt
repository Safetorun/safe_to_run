package io.github.dllewellyn.safetorun.conditional

/**
 * Class for building a list of 'conditionals' which can be used
 * for OS Checks
 */
class ConditionalBuilder internal constructor() {

    private val ands = mutableListOf<Conditional>()
    private val ors = mutableListOf<Conditional>()

    /**
     * Add a condition. This is syntactic sugar because
     * it has the same behaviour as [and]
     *
     * @param conditional the conditional to add
     */
    infix fun with(conditional: Conditional) {
        and(conditional)
    }

    /**
     * And. As in, and other conditions and this one
     * needs to be true to pass
     *
     * @param conditional the conditional to add
     */
    infix fun and(conditional: Conditional) {
        ands.add(conditional)
    }

    /**
     * Or. If this condition is true, it does not matter
     * what the state is of the other conditions
     *
     * @param conditional the conditional which needs to be true
     */
    infix fun or(conditional: Conditional) {
        ors.add(conditional)
    }

    internal fun build(): Conditional {
        return DefaultConditional(ands, ors)
    }

    private class DefaultConditional(
        private val ands: List<Conditional>,
        private val ors: List<Conditional>
    ) : Conditional {

        override fun invoke(): ConditionalResponse {

            val hasSingleOrPassed = hasASingleOrPassed()
            if (hasSingleOrPassed == null || hasSingleOrPassed.failed) {
                with(areAllAndsTrue()) {
                    if (failed) {
                        return this
                    }
                }
            }

            return ConditionalResponse(false)
        }

        private fun hasASingleOrPassed(): ConditionalResponse? {
            ors.forEach { or ->
                with(or()) {
                    if (!failed) {
                        return ConditionalResponse(false)
                    }
                }
            }

            return null
        }

        private fun areAllAndsTrue(): ConditionalResponse {
            ands.forEach { and ->
                with(and()) {
                    if (failed) {
                        return this
                    }
                }
            }

            return ConditionalResponse(false)
        }
    }
}
