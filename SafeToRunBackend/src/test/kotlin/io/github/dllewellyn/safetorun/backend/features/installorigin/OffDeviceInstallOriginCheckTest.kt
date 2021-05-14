package io.github.dllewellyn.safetorun.backend.features.installorigin

import com.google.common.truth.Truth
import io.github.dllewellyn.safetorun.models.models.InstallOriginDto
import io.github.dllewellyn.safetorun.backend.util.easilyAcceptableModel
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.micronaut.context.BeanContext
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class OffDeviceInstallOriginCheckTest {

    @Inject
    lateinit var beanContext: BeanContext

    @Test
    fun `test that the flow works with an off device blacklisted app check should fail`() {
        val result = easilyAcceptableModel.apply {
            installOrigin = InstallOriginDto().apply {
                installOriginPackageName = "com.not.valid"
            }
        }.installOriginCheckWithDefaults(beanContext)
        Truth.assertThat(result.canRun() is SafeToRunReport.SafeToRunReportFailure).isTrue()
    }

    @Test
    fun `test that the flow works with an off device blacklisted app check should pass`() {
        val result = easilyAcceptableModel.installOriginCheckWithDefaults(beanContext)
        Truth.assertThat(result.canRun() is SafeToRunReport.SafeToRunReportSuccess).isTrue()
    }
}
