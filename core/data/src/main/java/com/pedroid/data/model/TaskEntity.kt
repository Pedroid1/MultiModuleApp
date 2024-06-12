package com.pedroid.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_items")
data class TaskEntity(
    @PrimaryKey val uid: Int? = null,
    val title: String,
    val description: String? = null,
    val isChecked: Boolean = false
)