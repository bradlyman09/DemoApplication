package com.example.demoapplication.network

import com.example.demoapplication.network.data.response.error_response.ErrorResponse
import retrofit2.HttpException
import retrofit2.Response

sealed class ResultWrapper<out T> {
    data class ApiLoading<T>(val isLoading : Boolean) : ResultWrapper<T>()
    data class ApiSuccess<out T>(val body: T): ResultWrapper<T>()
    data class ApiError(val code : Int? = null, val errorMessage : ErrorResponse? = null): ResultWrapper<Nothing>()
}

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): ResultWrapper<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ResultWrapper.ApiSuccess(body)
        } else {
            ResultWrapper.ApiError(code = response.code(), errorMessage = ErrorResponse(response.message()))
        }
    } catch (e: HttpException) {
        ResultWrapper.ApiError(code = e.code(), errorMessage = ErrorResponse(e.message()))
    } catch (e: Throwable) {
        ResultWrapper.ApiError(code = 500, errorMessage = ErrorResponse(e.message ?: ""))
    }
}