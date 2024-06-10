package com.pedroid.common.base

data class BaseState<T>(
    val isLoading: Boolean = true,
    val data: T? = null,
    val error: String = ""
)
