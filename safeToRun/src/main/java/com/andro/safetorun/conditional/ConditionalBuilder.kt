package com.andro.safetorun.conditional

internal class ConditionalBuilder {

    private val ands = mutableListOf<Conditional>()
    private val ors = mutableListOf<Conditional>()
    private val nots = mutableListOf<Conditional>()


    infix fun with(conditional: Conditional) {
        and(conditional)
    }

    infix fun and(conditional: Conditional) {
        ands.add(conditional)
    }

    infix fun or(conditional: Conditional) {
        ors.add(conditional)
    }

    infix fun not(conditional: Conditional) {
        nots.add(conditional)
    }

    internal fun build(): Conditional {
        return DefaultConditional(ands, ors, nots)
    }

    private class DefaultConditional(
        private val ands: List<Conditional>,
        private val ors: List<Conditional>,
        private val nots: List<Conditional>,
    ) : Conditional {

        override fun invoke(): Boolean =
            (areAllAndsTrue() || hasASingleOrPassed()) && thereAreNoNotsThatAreTrue()

        private fun thereAreNoNotsThatAreTrue(): Boolean {
            nots.forEach { not ->
                if (not()) {
                    return false
                }
            }

            return true
        }

        private fun hasASingleOrPassed(): Boolean {
            var singleOrPassed = false

            ors.forEach { or ->
                if (or()) {
                    singleOrPassed = true
                }
            }
            return singleOrPassed
        }

        private fun areAllAndsTrue(): Boolean {
            var shouldContinue = true

            ands.forEach { and ->
                if (!and()) {
                    shouldContinue = false
                }
            }
            return shouldContinue
        }

    }
}