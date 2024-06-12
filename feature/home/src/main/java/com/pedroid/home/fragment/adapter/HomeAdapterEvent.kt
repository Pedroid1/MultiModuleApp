package com.pedroid.home.fragment.adapter

import com.pedroid.domain.model.Task

interface HomeAdapterEvent {

    fun addTask()

    fun editTask(task: Task)

    fun toggleChecked(task: Task)
}