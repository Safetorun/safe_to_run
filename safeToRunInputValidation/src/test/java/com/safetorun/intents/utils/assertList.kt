package com.safetorun.intents.utils

import com.google.common.truth.Ordered
import com.google.common.truth.Truth.assertThat

fun <T> List<T>.assertContains(vararg args: T): Ordered = assertThat(this).containsExactly(*args)

fun Boolean.assertTrue() = assertThat(this).isTrue()
fun Boolean.assertFalse() = assertThat(this).isFalse()
