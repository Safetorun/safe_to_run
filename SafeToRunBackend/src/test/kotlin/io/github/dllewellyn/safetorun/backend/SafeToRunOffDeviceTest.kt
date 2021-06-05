package io.github.dllewellyn.safetorun.backend

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.backend.features.signature.OffDeviceSignatureVerificationStrings
import io.github.dllewellyn.safetorun.backend.features.signature.verifySignatureCheck
import io.github.dllewellyn.safetorun.backend.util.easilyAcceptableModel
import io.github.dllewellyn.safetorun.configure
import io.github.dllewellyn.safetorun.models.models.SignatureVerificationDto
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.micronaut.context.BeanContext
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SafeToRunOffDeviceTest {

    private val beanContext: BeanContext = mockk()

    @BeforeAll
    fun setup() {
        every { beanContext.getBean(OffDeviceSignatureVerificationStrings::class.java) } returns
                OffDeviceSignatureVerificationStrings(
                    "pass", "pass", "not found"
                )
    }

    @Test
    fun `test that safe to run off device will return a value based on configuration`() {

        val safeToRunoffDevice = SafeToRunOffDevice(
            configure {
                easilyAcceptableModel.apply {
                    verifySignatureCheck(beanContext, "signaturesample").error()
                    signatureVerification =
                        SignatureVerificationDto().apply { signatureVerificationString = "signaturesample" }
                }
            }
        )

        assertThat((safeToRunoffDevice.isSafeToRun() as SafeToRunReport.MultipleReports).reports.first())
            .isInstanceOf(SafeToRunReport.SafeToRunReportSuccess::class.java)
    }
}
