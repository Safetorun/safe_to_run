package com.safetorun.conditional

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase

internal class ConditionalBuilderTest : TestCase() {

    fun `test that we can add two ands and if both pass all passes`() {
        // Given
        val builtResult = conditionalBuilder {
            and { ConditionalResponse(false) }
            and { ConditionalResponse(false) }
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result.failed).isFalse()
    }

    fun `test that we can add two ands and if one fails both fail`() {
        // Given
        val failMessage = "failure message"
        val builtResult = conditionalBuilder {
            and { ConditionalResponse(true, failMessage) }
            and { ConditionalResponse(false) }
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result.failed).isTrue()
        assertThat(result.message).isEqualTo(failMessage)
    }

    fun `test that if we an and and an or we see the right message`() {
        // Given
        val failMessage = "failure message"

        val builtResult = conditionalBuilder {
            failIf { ConditionalResponse(true, failMessage) }
            unless { ConditionalResponse(true) }
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result.failed).isTrue()
        assertThat(result.message).isEqualTo(failMessage)
    }

    fun `test that when if we add an and that is true and a or that is false then we pass`() {
        // Given
        val builtResult = conditionalBuilder {
            and { ConditionalResponse(true) }
            or { ConditionalResponse(false) }
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result.failed).isFalse()
    }

    fun `test that when if we add an and that is false and a or that is true then we pass`() {
        // Given
        val builtResult = conditionalBuilder {
            and { ConditionalResponse(false) }
            or { ConditionalResponse(false) }
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result.failed).isFalse()
    }

    fun `test if at least one or passes then we pass`() {
        // Given
        val builtResult = conditionalBuilder {
            and { ConditionalResponse(true) }
            or { ConditionalResponse(true) }
            or { ConditionalResponse(false) }
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result.failed).isFalse()
    }
}
