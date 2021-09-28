package io.github.dllewellyn.safetorun.intents.utils

import com.google.common.truth.Truth

fun <T> List<T>.assertContains(vararg args: T) = Truth.assertThat(this).containsExactly(*args)
