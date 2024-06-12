package com.pedroid.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface DataResource<out T> {
    data class Success<T>(val data: T) : DataResource<T>
    data class Error(val exception: Throwable? = null) : DataResource<Nothing>
    object Loading : DataResource<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<DataResource<T>> {
    return this
        .map<T, DataResource<T>> {
            DataResource.Success(it)
        }
        .onStart { emit(DataResource.Loading) }
        .catch { emit(DataResource.Error(it)) }
}
