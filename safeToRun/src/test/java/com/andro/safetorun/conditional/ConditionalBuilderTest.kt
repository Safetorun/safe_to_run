package com.andro.safetorun.conditional

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase

class ConditionalBuilderTest : TestCase() {

    fun `test that we can add two ands and if both pass all passes`() {
        // Given
        val builtResult = conditionalBuilder {
            and { true }
            and { true }
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result).isTrue()
    }

    fun `test that we can add two ands and if one fails both fail`() {
        // Given
        val builtResult = conditionalBuilder {
            and { true }
            and { false }
            
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result).isFalse()
    }

    fun `test that we can add an and and a not if the and passes and the not is true we returns false`() {
        // Given
        val builtResult = conditionalBuilder {
            and { true }
            not { true }
            
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result).isFalse()
    }

    fun `test that we can add an and and a not if the and passes and the not is false we returns true`() {
        // Given
        val builtResult = conditionalBuilder {
            and { true }
            not { false }
            
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result).isTrue()
    }

    fun `test that we can add an and and a not if the and fails and the not is false we returns false`() {
        // Given
        val builtResult = conditionalBuilder {
            and { false }
            not { false }
            
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result).isFalse()
    }

    fun `test that when if we add an and that is true and a or that is false then we pass`() {
        // Given
        val builtResult = conditionalBuilder {
            and { true }
            or { false }
            
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result).isTrue()
    }

    fun `test that when if we add an and that is false and a or that is true then we pass`() {
        // Given
        val builtResult = conditionalBuilder {
            and { false }
            or { true }
            
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result).isTrue()
    }

    fun `test that when if we add an and that is false and a or that is false then we fail`() {
        // Given
        val builtResult = conditionalBuilder {
            and { false }
            or { false }
            
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result).isFalse()
    }

    fun `test that when if we add an and that is true and a or that is true but a not is true then we fail`() {
        // Given
        val builtResult = conditionalBuilder {
            and { true }
            or { true }
            not { true }
            
        }

        // When
        val result = builtResult()

        // Then
        assertThat(result).isFalse()
    }
}