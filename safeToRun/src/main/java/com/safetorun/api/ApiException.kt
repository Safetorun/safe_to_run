package com.safetorun.api

/**
 * An exception thrown when the API has returned a non 200 response
 *
 * @param exceptionMessage message to use when passing through to the constructor
 * of throwable
 */
class ApiException private constructor(exceptionMessage: String) : Throwable(exceptionMessage) {
    companion object {
        fun throwApiException(message: String): ApiException =  ApiException(message)
    }
}
