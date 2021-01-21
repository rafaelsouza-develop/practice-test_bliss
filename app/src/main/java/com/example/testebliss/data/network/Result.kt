package com.example.testebliss.data.network

sealed class Result<out T> {
    class Success<out T>(val data: T) : Result<T>()
    class Failure(val throwable: Throwable) : Result<Nothing>()
}