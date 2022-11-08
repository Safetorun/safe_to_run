package com.safetorun.resilienceshared.builders

import com.google.common.truth.Truth.assertThat
import com.safetorun.resilienceshared.dto.CheckComparator
import com.safetorun.resilienceshared.dto.IntCheckType
import com.safetorun.resilienceshared.dto.Severity
import com.safetorun.resilienceshared.dto.StringCheckType
import org.junit.Test

internal class BaseSingleOSCheckBuilderTest {

    @Test
    fun `test that we can build a single check for off device builder with no values`() {
        val singleBuilder = BaseSingleOSCheckBuilder()
            .build()

        assertThat(singleBuilder.allIntChecks).isEmpty()
        assertThat(singleBuilder.allStringChecks).isEmpty()
    }

    @Test
    fun `test that we can build a single check for off device builder with string`() {
        val singleBuilder = BaseSingleOSCheckBuilder()
            .apply {
                StringCheckType.BannedBoard.failIf("TEST", CheckComparator.EQUALS)
                StringCheckType.BannedDevice.failIf("ABC", CheckComparator.GREATER_THAN)
                StringCheckType.BannedHardware.unless("TEST", CheckComparator.EQUALS)
                IntCheckType.MinOsCheck.unless(1, CheckComparator.GREATER_THAN)
            }
            .build()

        assertThat(singleBuilder.unlessIntChecks.map { it.checkType }).contains(IntCheckType.MinOsCheck)
        assertThat(singleBuilder.unlessStringChecks.map { it.checkType }).contains(StringCheckType.BannedHardware)

        assertThat(singleBuilder.unlessIntChecks.map { it.intValue }).contains(1)
        assertThat(singleBuilder.unlessStringChecks.map { it.stringValue }).contains("TEST")

        assertThat(singleBuilder.unlessStringChecks.map { it.comparator }).contains(CheckComparator.EQUALS)
        assertThat(singleBuilder.unlessIntChecks.map { it.comparator }).contains(CheckComparator.GREATER_THAN)

        assertThat(singleBuilder.allStringChecks.map { it.checkType }).containsAtLeast(
            StringCheckType.BannedDevice,
            StringCheckType.BannedBoard
        )
        assertThat(singleBuilder.allStringChecks.map { it.comparator }).containsAtLeast(
            CheckComparator.EQUALS,
            CheckComparator.GREATER_THAN
        )

        assertThat(singleBuilder.allStringChecks.map { it.stringValue }).containsAtLeast(
            "TEST",
            "ABC"
        )
    }

    @Test
    fun `test that we can build a single check for off device builder with an int`() {
        val singleBuilder = BaseSingleOSCheckBuilder()
            .apply {
                IntCheckType.MinOsCheck.failIf(2, CheckComparator.GREATER_THAN)
            }
            .build()

        assertThat(singleBuilder.allIntChecks.map { it.checkType }).contains(IntCheckType.MinOsCheck)
        assertThat(singleBuilder.allIntChecks.map { it.comparator }).contains(CheckComparator.GREATER_THAN)
        assertThat(singleBuilder.allIntChecks.map { it.intValue }).contains(2)
    }
}
