package com.pedroid.home.fragment

import android.os.Build
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pedroid.feature.main.home.databinding.FragmentTaskDetailsDialogBinding
import com.pedroid.model.Task

class TaskDetailDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val TASK_EXTRA_KEY = "task_key"
    }

    private lateinit var binding: FragmentTaskDetailsDialogBinding
    private val taskArgs by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(TASK_EXTRA_KEY, Task::class.java)
        } else {
            arguments?.getSerializable(TASK_EXTRA_KEY) as Task
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskDetailsDialogBinding.inflate(inflater, container, false)
        taskArgs?.let {
            binding.task = taskArgs
        }
        return binding.root
    }
}