package io.github.dllewellyn.safetorun.models.models

import kotlinx.serialization.Serializable

@Serializable
/**
 * DTO For the install origin (i.e. which app store installed this app)
 */
class InstallOriginDto {
    var installOriginPackageName: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InstallOriginDto

        if (installOriginPackageName != other.installOriginPackageName) return false

        return true
    }

    override fun hashCode(): Int {
        return installOriginPackageName.hashCode()
    }

    override fun toString(): String {
        return "InstallOriginDto(installOriginPackageName='$installOriginPackageName')"
    }
}
