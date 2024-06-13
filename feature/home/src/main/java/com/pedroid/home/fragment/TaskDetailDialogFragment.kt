package com.pedroid.home.fragment

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.pedroid.feature.home.databinding.FragmentTaskDetailsDialogBinding

class TaskDetailDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentTaskDetailsDialogBinding
    private val args: TaskDetailDialogFragmentArgs by navArgs()
    private val taskArgs by lazy {
        args.task
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