package io.github.dllewellyn.safetorun.backend.features.oscheck

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.backend.util.easilyAcceptableModel
import io.github.dllewellyn.safetorun.conditional.conditionalBuilder
import io.github.dllewellyn.safetorun.features.oscheck.minOsVersion
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.micronaut.context.BeanContext
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class OSInformationQueryDeviceAdapterTest {

    @Inject
    lateinit var beanContext: BeanContext

    @Test
    fun `test that we can perform a safe to run check off device for the os information`() {
        val result = with(easilyAcceptableModel.osInformation()) {
            osDetectionCheck(
                beanContext,
                conditionalBuilder {
                    with(minOsVersion(32))
                }
            )
        }.canRun()

        assertThat(result is SafeToRunReport.MultipleReports).isTrue()
        assertThat(
            (result as SafeToRunReport.MultipleReports).reports.first()
                    is SafeToRunReport.SafeToRunReportFailure
        ).isTrue()
    }

    @Test
    fun `test that we can perform a safe to run check off device for the os information which passes`() {
        val result = with(easilyAcceptableModel.osInformation()) {
            osDetectionCheck(
                beanContext,
                conditionalBuilder {
                    with(minOsVersion(30))
                }
            )
        }.canRun()

        assertThat(result is SafeToRunReport.SafeToRunReportSuccess).isTrue()
    }
}
