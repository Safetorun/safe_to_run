package com.safetorun.api

/**
 * An exception thrown when the API has returned a non 200 response
 *
 * @param exceptionMessage message to use when passing through to the constructor
 * of throwable
 */
class ApiException(exceptionMessage: String) : Throwable(exceptionMessage)
