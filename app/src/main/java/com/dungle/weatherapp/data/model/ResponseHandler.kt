package com.dungle.weatherapp.data.model

import retrofit2.HttpException
import java.net.SocketTimeoutException

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): DataResult<T> {
        return DataResult.success(data)
    }

    fun <T : Any> handleException(e: Exception): DataResult<T> {
        return when (e) {
            is HttpException -> DataResult.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> DataResult.error(
                getErrorMessage(ErrorCodes.SocketTimeOut.code),
                null
            )
            is NullPointerException -> DataResult.error(getErrorMessage(404), null) // Offline search will return null if search result not match
            else -> DataResult.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }
}

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}
