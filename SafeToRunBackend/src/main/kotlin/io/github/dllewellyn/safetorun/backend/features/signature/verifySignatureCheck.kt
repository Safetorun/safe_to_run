package io.github.dllewellyn.safetorun.backend.features.signature

import io.github.dllewellyn.safetorun.checks.SafeToRunCheck
import io.github.dllewellyn.safetorun.features.signatureverify.verifySignatureConfiguration
import io.github.dllewellyn.safetorun.models.models.DeviceInformationDto
import io.micronaut.context.BeanContext

/**
 * Retrieve a safe to run check which will verify that the app has the correct
 * signature as specific in the input
 *
 * E.g.
 *
 * `
 * verifySignatureCheck(context, "asdbasd==").error()
 * `
 *
 * @param context the bean context for micronaut to use for DI
 * @param signatures the list of signatures that you can check against
 */
fun DeviceInformationDto.verifySignatureCheck(
    context: BeanContext,
    vararg signatures: String
): SafeToRunCheck {
    return verifySignatureConfiguration(
        context.getBean(OffDeviceSignatureVerificationStrings::class.java),
        { signatureVerification.signatureVerificationString },
        *signatures
    )
}
