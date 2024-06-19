package com.pedroid.home.fragment.adapter

import com.pedroid.model.Task

interface HomeAdapterEvent {

    fun addTask()

    fun editTask(task: Task)

    fun toggleChecked(task: Task)

    fun detail(task: Task)
}