package com.andro.safetorun.features.oscheck.composites

class OsDetectionRuleBuilder {
    private val osDetection = mutableListOf<OsConditional>()

    operator fun plus(conditional: OsConditional) {
        osDetection.add(conditional)
    }

    fun build() = OsDetectionRule(osDetection)
}

class OsDetectionRule(private val osConditionals: List<OsConditional>) : OsConditional {

    override fun invoke(): Boolean {
        osConditionals.forEach { conditionals ->
            if (!conditionals.invoke()) {
                return false
            }
        }

        return true
    }
}