package com.pedroid.model

import java.io.Serializable

data class Task(
    val uid: Int? = null,
    val title: String,
    val description: String? = null,
    val isChecked: Boolean = false
): Serializable
