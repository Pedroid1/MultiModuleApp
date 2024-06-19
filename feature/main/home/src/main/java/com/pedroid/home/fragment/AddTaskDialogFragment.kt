package com.pedroid.home.fragment

import android.os.Build
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.pedroid.feature.main.home.R
import com.pedroid.feature.main.home.databinding.FragmentAddTaskDialogListDialogBinding
import com.pedroid.home.navigation.HomeNavigationNode
import com.pedroid.model.Task
import com.pedroid.navigation.navigateWithArgs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddTaskDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val TASK_EXTRA_KEY = "task_extra"
    }

    @Inject
    lateinit var navController: NavController

    private val viewModel by lazy { ViewModelProvider(requireActivity())[HomeViewModel::class.java] }
    private lateinit var binding: FragmentAddTaskDialogListDialogBinding
    private val task: Task? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(TASK_EXTRA_KEY, Task::class.java)
        } else {
            arguments?.getSerializable(TASK_EXTRA_KEY) as? Task
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskDialogListDialogBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        val buttonTextId = task?.let {
            R.string.save
        } ?: R.string.add
        binding.addBtn.text = requireContext().getString(buttonTextId)

        task?.let {
            binding.taskTitleEdt.setText(it.title)
            binding.taskDescriptionEdt.setText(it.description)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.apply {
            validationLiveData.observe(viewLifecycleOwner) { result ->
                result.getContentIfNotHandled()?.let { uiText ->
                    binding.taskTitleTil.error = uiText.errorMessage?.asString(requireContext())
                }
            }

            insertResultLiveData.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let { result ->
                    if(result) {
                        dismissNow()
                    }
                }
            }
        }
    }

    private fun setupListener() {
        binding.addBtn.setOnClickListener {
            val title = binding.taskTitleEdt.text.toString()
            val description = binding.taskDescriptionEdt.text.toString()
            task?.let {
                val taskCopy = it.copy(title = title, description = description)
                if(task != taskCopy) {
                    viewModel.insertTaskWithFieldsValidation(taskCopy)
                } else {
                    dismissNow()
                }
            } ?: viewModel.insertTaskWithFieldsValidation(Task(title = title, description = description))
        }
    }
}