package io.github.dllewellyn.safetorun.backend.models

import io.micronaut.core.annotation.Introspected

@Introspected
class ConfirmVerificationRequestDto {
    var signature: String = ""
    var apiKey: String = ""
}