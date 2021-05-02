package io.github.dllewellyn.safetorun

import io.mockk.InternalPlatformDsl.toStr
import io.mockk.MockKAnswerScope

fun MockKAnswerScope<String, String>.stringWithoutSuffixForArg(argNumber : Int) =
    args[argNumber].toStr().removePrefix("[").removeSuffix("]")