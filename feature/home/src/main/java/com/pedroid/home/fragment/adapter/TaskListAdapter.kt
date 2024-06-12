package com.pedroid.home.fragment.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.pedroid.feature.home.databinding.EmptyItemBinding
import com.pedroid.feature.home.databinding.TaskItemBinding

class TaskListAdapter(private val eventListener: HomeAdapterEvent) :
    ListAdapter<TaskListAdapterItem, TaskListAdapter.TaskViewHolder>(DIFFUTILS) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return when (EnumTaskListAdapterViewType.getEnumByOrdinal(viewType)) {
            EnumTaskListAdapterViewType.TASK -> {
                TaskViewHolder.TaskItem(
                    TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    eventListener
                )
            }

            else -> {
                return TaskViewHolder.EmptyItem(
                    EmptyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    eventListener
                )
            }
        }
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) = when (holder) {
        is TaskViewHolder.TaskItem -> {
            holder.bind(
                getItem(position) as TaskListAdapterItem.TaskItem,

                )
        }

        is TaskViewHolder.EmptyItem -> {
            holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }

    sealed class TaskViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        class TaskItem(
            private val binding: TaskItemBinding,
            private val eventListener: HomeAdapterEvent
        ) : TaskViewHolder(binding) {

            fun bind(taskItem: TaskListAdapterItem.TaskItem) {
                binding.task = taskItem.task
                binding.eventListener = eventListener
                if (taskItem.task.isChecked) {
                    binding.title.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    binding.description.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    binding.title.paintFlags = binding.title.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    binding.description.paintFlags = binding.description.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
            }
        }

        class EmptyItem(
            private val binding: EmptyItemBinding,
            private val eventListener: HomeAdapterEvent
        ) : TaskViewHolder(binding) {

            fun bind() {
                binding.eventListener = eventListener
            }
        }
    }

    companion object {
        val DIFFUTILS = object : DiffUtil.ItemCallback<TaskListAdapterItem>() {
            override fun areItemsTheSame(
                oldItem: TaskListAdapterItem,
                newItem: TaskListAdapterItem
            ): Boolean {
                return when {
                    oldItem is TaskListAdapterItem.TaskItem && newItem is TaskListAdapterItem.TaskItem -> {
                        return when {
                            oldItem.task.uid != newItem.task.uid -> false
                            oldItem.task.title != newItem.task.title -> false
                            oldItem.task.isChecked != newItem.task.isChecked -> false
                            else -> true
                        }
                    }

                    else -> false
                }
            }

            override fun areContentsTheSame(
                oldItem: TaskListAdapterItem,
                newItem: TaskListAdapterItem
            ): Boolean {
                return when {
                    oldItem is TaskListAdapterItem.TaskItem && newItem is TaskListAdapterItem.TaskItem -> {
                        return when {
                            oldItem.task.uid != newItem.task.uid -> false
                            oldItem.task.title != newItem.task.title -> false
                            oldItem.task.isChecked != newItem.task.isChecked -> false
                            else -> true
                        }
                    }

                    else -> false
                }
            }
        }
    }
}