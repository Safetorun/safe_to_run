package com.safetorun.features.oscheck

import junit.framework.TestCase

internal class OsDetectionCheckTest : TestCase() {
    fun `test safeToRunCombinedCheck combines multiple rules when you fail if`() {
        val result = safeToRunCombinedCheck(
            listOf(
                { true },
                { false }
            )
        )

        assertTrue(result)
    }

    fun `test safeToRunCombinedCheck combines multiple rules when you fail if 2`() {
        val result = safeToRunCombinedCheck(
            listOf(
                { false },
                { true }
            )
        )

        assertTrue(result)
    }

    fun `test safeToRunCombinedCheck passes if an unless returns false`() {
        val result = safeToRunCombinedCheck(
            listOf(
                { false },
                { true }
            ),
            listOf { false }
        )

        assertFalse(result)
    }

    fun `test safeToRunCombinedCheck fails if an unless returns true`() {
        val result = safeToRunCombinedCheck(
            listOf(
                { false },
                { true }
            ),
            listOf { true }
        )

        assertTrue(result)
    }

    fun `test safeToRunCombinedCheck passes if unless fails but checks pass`() {
        val result = safeToRunCombinedCheck(
            listOf { false },
            listOf { true }
        )

        assertFalse(result)
    }
}
