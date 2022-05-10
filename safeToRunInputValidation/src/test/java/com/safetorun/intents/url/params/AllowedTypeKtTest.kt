package com.safetorun.intents.url.params

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase

internal class AllowedTypeKtTest : TestCase() {

    fun `test allowed types parses any as true for anything`() {
        assertThat(AllowedType.Any.matchesAllowedType("")).isTrue()
    }

    fun `test allowed types parses any as true for valid string`() {
        assertThat(AllowedType.String.matchesAllowedType("string")).isTrue()
    }

    fun `test allowed types parses any as true for valid boolean`() {
        assertThat(AllowedType.Boolean.matchesAllowedType("true")).isTrue()
    }

    fun `test allowed types parses any as true for valid boolean (false)`() {
        assertThat(AllowedType.Boolean.matchesAllowedType("false")).isTrue()
    }

    fun `test allowed types parses any as false for invalid boolean`() {
        assertThat(AllowedType.Boolean.matchesAllowedType("abc")).isFalse()
    }

    fun `test allowed types parses int as false for invalid int`() {
        assertThat(AllowedType.Int.matchesAllowedType("abc")).isFalse()
    }

    fun `test allowed types parses int as try for invalid int`() {
        assertThat(AllowedType.Int.matchesAllowedType("123")).isTrue()
    }


    fun `test allowed types parses int as false for invalid double`() {
        assertThat(AllowedType.Double.matchesAllowedType("abc")).isFalse()
    }

    fun `test allowed types parses int as try for invalid double`() {
        assertThat(AllowedType.Double.matchesAllowedType("123.0")).isTrue()
    }

}
