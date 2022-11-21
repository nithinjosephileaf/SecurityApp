package com.nithi.securityauthapp.utility

sealed class ResponseState<T> {
    data class Error<T>(val error: String) : ResponseState<T>()
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Failed<T>(val exception: Exception) : ResponseState<T>()
}