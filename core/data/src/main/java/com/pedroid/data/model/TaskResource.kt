package com.pedroid.data.model

import com.pedroid.database.model.TaskEntity
import com.pedroid.model.Task

fun Task.toEntity() = TaskEntity(
    uid, title, description, isChecked
)