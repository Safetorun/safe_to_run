package io.github.dllewellyn.safetorun.backend

import io.github.dllewellyn.safetorun.SafeToRun
import io.github.dllewellyn.safetorun.SafeToRunSingle
import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.configure
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class SafeToRun {

    @Singleton
    fun safeToRun(): SafeToRun = SafeToRunSingle.apply {
        init(
            configure {
            }
        )
    }
}