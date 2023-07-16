package com.safetorun.logger.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

/**
 * The safe to run verify type to be used
 */
@Serializable
enum class VerifyType {
    Intent,
    Url,
    File
}


/**
 * Fire events to safe to run backend
 */
@Serializable
sealed class SafeToRunEvents(
    val time: Long = Calendar.getInstance().time.time,
    val uuid: String = UUID.randomUUID().toString(),
    val metadata: AppMetadata
) {

    /**
     * A check failed
     */
    @Serializable
    @SerialName("FailedCheck")
    data class FailedCheck(
        val deviceInformation: DeviceInformation,
        val appMetadata: AppMetadata,
        val checkName: String
    ) :
        SafeToRunEvents(metadata = appMetadata) {
        companion object {
            /**
             * Create an empty event
             *
             * @param name the name of the check
             */
            fun empty(name: String) =
                FailedCheck(DeviceInformation.empty(), AppMetadata.empty(), name)
        }
    }

    /**
     * A check succeeded
     */
    @Serializable
    @SerialName("SucceedCheck")
    data class SucceedCheck(
        val deviceInformation: DeviceInformation,
        val appMetadata: AppMetadata,
        val checkName: String
    ) :
        SafeToRunEvents(metadata = appMetadata) {
        companion object {
            /**
             * Create an empty event
             *
             * @param name the name of the check
             */
            fun empty(name: String) =
                SucceedCheck(DeviceInformation.empty(), AppMetadata.empty(), name)
        }
    }

    /**
     * A failed verification
     */
    @Serializable
    @SerialName("FailedVerify")
    data class FailedVerify(
        val deviceInformation: DeviceInformation,
        val appMetadata: AppMetadata,
        val checkName: String,
        val verifyType: VerifyType,
        val extra: String?
    ) :
        SafeToRunEvents(metadata = appMetadata) {
        companion object {
            /**
             * Create an empty event for failed verification
             *
             * @param name the name of the check
             * @param verifyType the type of verification (Intent, Url, or File)
             * @param extra additional information (nullable)
             */
            fun empty(name: String, verifyType: VerifyType, extra: String? = null) =
                FailedVerify(
                    DeviceInformation.empty(),
                    AppMetadata.empty(),
                    name,
                    verifyType,
                    extra
                )
        }
    }

    /**
     * A successful verification
     */
    @Serializable
    @SerialName("SuccessVerify")
    data class SuccessVerify(
        val deviceInformation: DeviceInformation,
        val appMetadata: AppMetadata,
        val checkName: String,
        val verifyType: VerifyType,
        val extra: String?
    ) :
        SafeToRunEvents(metadata = appMetadata) {
        companion object {
            /**
             * Create an empty event for successful verification
             *
             * @param name the name of the check
             * @param verifyType the type of verification (Intent, Url, or File)
             * @param extra additional information (nullable)
             */
            fun empty(name: String, verifyType: VerifyType, extra: String? = null) =
                SuccessVerify(
                    DeviceInformation.empty(),
                    AppMetadata.empty(),
                    name,
                    verifyType,
                    extra
                )
        }
    }
}
