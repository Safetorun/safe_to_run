package io.github.dllewellyn.safetorun.backend.features.signature

import com.google.common.truth.Truth.assertThat
import io.github.dllewellyn.safetorun.backend.util.easilyAcceptableModel
import io.github.dllewellyn.safetorun.models.models.SignatureVerificationDto
import io.github.dllewellyn.safetorun.reporting.SafeToRunReport
import io.micronaut.context.BeanContext
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class VerifySignatureCheckKtTest {

    @Inject
    lateinit var beanContext: BeanContext
    lateinit var expectedStrings: OffDeviceSignatureVerificationStrings

    @BeforeEach
    fun setup() {
        expectedStrings = beanContext.getBean(OffDeviceSignatureVerificationStrings::class.java)
    }

    @Test
    fun `test that we can retrieve a response based on a missing signature`() {
        // Given
        val model = easilyAcceptableModel.apply {
            signatureVerification = SignatureVerificationDto().apply {
                signatureVerificationString = null
            }
        }

        // When
        val result = model.verifySignatureCheck(beanContext).canRun() as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(result.failureMessage).isEqualTo(expectedStrings.signatureNotFoundMessage())
    }

    @Test
    fun `test that we can retrieve a response based on a correct signature`() {
        // Given
        val model = easilyAcceptableModel.apply {
            signatureVerification = SignatureVerificationDto().apply {
                signatureVerificationString = "correct"
            }
        }
        // When
        val result = model.verifySignatureCheck(beanContext, "correct").canRun()
                as SafeToRunReport.SafeToRunReportSuccess

        // Then
        assertThat(result.successMessage).isEqualTo(expectedStrings.signatureMatchesMessage())
    }

    @Test
    fun `test that we can retrieve a response based on a incorrect signature`() {
        // Given
        val model = easilyAcceptableModel.apply {
            signatureVerification = SignatureVerificationDto().apply {
                signatureVerificationString = "abc"
            }
        }

        // When
        val result =
            model.verifySignatureCheck(beanContext, "def").canRun() as SafeToRunReport.SafeToRunReportFailure

        // Then
        assertThat(result.failureMessage).isEqualTo(expectedStrings.signatureNotMatchedMessage("abc"))
    }
}
