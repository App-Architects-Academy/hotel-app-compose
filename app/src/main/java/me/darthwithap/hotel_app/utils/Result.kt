package me.darthwithap.hotel_app.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

sealed interface Result<out T> {
  data class Success<T>(val data: T) : Result<T>
  data class Error(val exception: Throwable? = null) : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
  return this
    .map<T, Result<T>> { Result.Success(it) }
    .catch { emit(Result.Error(it)) }
}