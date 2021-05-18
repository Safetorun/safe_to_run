package io.github.dllewellyn.safetorun

import android.os.Build
import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.features.oscheck.OSInformationQueryAndroid
import io.mockk.mockkStatic
import junit.framework.TestCase
import java.lang.reflect.Field
import java.lang.reflect.Modifier

internal class OSInformationQueryAndroidTest : TestCase() {

    fun `test that os information query returns data from build class`() {
        // Given
        mockkStatic(Build::class)

        val sdkInt = 123
        val manufacturer = "manufacturer"
        val model = "brand"

        mockBuildField(sdkInt, "SDK_INT", Build.VERSION::class.java)
        mockBuildField(manufacturer, "MANUFACTURER", Build::class.java)
        mockBuildField(model, "MODEL", Build::class.java)

        // When
        val osInformationQuery = OSInformationQueryAndroid()

        // Then
        assertThat(osInformationQuery.osVersion()).isEqualTo(sdkInt)
        assertThat(osInformationQuery.manufacturer()).isEqualTo(manufacturer)
        assertThat(osInformationQuery.model()).isEqualTo(model)
    }

    private fun <T> mockBuildField(v: T, fieldName: String, clazz: Class<*>) {
        val sdkIntField = clazz.getField(fieldName)
        sdkIntField.isAccessible = true
        Field::class.java.getDeclaredField("modifiers").also {
            it.isAccessible = true
            it.set(sdkIntField, sdkIntField.modifiers and Modifier.FINAL.inv())
        }
        sdkIntField.set(null, v)
    }
}
