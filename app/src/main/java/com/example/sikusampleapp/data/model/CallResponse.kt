package com.example.sikusampleapp.data.model

sealed interface CallResponse<out T>

data class SuccessResult<T>(val data: List<T>) : CallResponse<T>

data class ErrorResult(val error: String) : CallResponse<Nothing>

