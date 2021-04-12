package com.andro.safetorun.features.oscheck.composites

class OsConditionalBuilder {

    private val ands = mutableListOf<OsConditional>()
    private val ors = mutableListOf<OsConditional>()
    private val nots = mutableListOf<OsConditional>()

    infix fun and(conditional: OsConditional) {
        ands.add(conditional)
    }

    infix fun or(conditional: OsConditional) {
        ors.add(conditional)
    }

    infix fun not(conditional: OsConditional) {
        nots.add(conditional)
    }

    internal fun build(): OsConditional {
        return DefaultOsConditional(ands, ors, nots)
    }

    private class DefaultOsConditional(
        private val ands: List<OsConditional>,
        private val ors: List<OsConditional>,
        private val nots: List<OsConditional>,
    ) : OsConditional {

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