package io.github.dllewellyn.safetorun.backend.features.blacklistedapps

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.backend.util.easilyAcceptableModel
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.micronaut.context.BeanContext
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import javax.inject.Inject

@MicronautTest
internal class OffDeviceBlacklistedAppCheckTest {

    @Inject
    lateinit var beanContext: BeanContext

    @Test
    fun `test that the flow works with an off device blacklisted app check should fail`() {
        val result = easilyAcceptableModel.blacklistedAppCheck(beanContext) {
            +easilyAcceptableModel.blacklistedApp.installedPackages.first()
        }

        assertThat(result.canRun() is SafeToRunReport.MultipleReports).isTrue()

        assertThat(
            (result.canRun() as SafeToRunReport.MultipleReports)
                .reports.first() is SafeToRunReport.SafeToRunReportFailure
        ).isTrue()
    }

    @Test
    fun `test that the flow works with an off device blacklisted app check should pass`() {
        val result = easilyAcceptableModel.blacklistedAppCheck(beanContext) {
            +"com.package.does.not.exist"
        }

        assertThat(result.canRun() is SafeToRunReport.SafeToRunReportSuccess).isTrue()
    }
}
