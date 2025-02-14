package com.ev.bluepixel.data


sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

// Excepciones personalizadas
class NetworkException(message: String) : Exception(message)
class ServerException(message: String) : Exception(message)
class UnknownException(message: String) : Exception(message)