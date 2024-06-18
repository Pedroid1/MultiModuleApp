package com.pedroid.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pedroid.model.Task

@Entity(tableName = "task_items")
data class TaskEntity(
    @PrimaryKey val uid: Int? = null,
    val title: String,
    val description: String? = null,
    val isChecked: Boolean = false
)

fun TaskEntity.toExternalModel() = Task(
    uid, title, description, isChecked
)