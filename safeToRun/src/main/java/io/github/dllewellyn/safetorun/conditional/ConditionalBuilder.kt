package io.github.dllewellyn.safetorun.conditional

class ConditionalBuilder {

    private val ands = mutableListOf<Conditional>()
    private val ors = mutableListOf<Conditional>()

    infix fun with(conditional: Conditional) {
        and(conditional)
    }

    infix fun and(conditional: Conditional) {
        ands.add(conditional)
    }

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

            with(hasASingleOrPassed()) {
                if (this != null && !failed) {
                    return ConditionalResponse(false)
                }
            }

            with(areAllAndsTrue()) {
                if (failed) {
                    return this
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