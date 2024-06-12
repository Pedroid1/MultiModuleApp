package com.pedroid.domain.model

import com.pedroid.data.model.TaskEntity

data class Task(
    val uid: Int? = null,
    val title: String,
    val description: String? = null,
    val isChecked: Boolean = false
)

fun Task.toEntity() = TaskEntity(uid, title, description, isChecked)

fun TaskEntity.toModel() = Task(uid, title, description, isChecked)
