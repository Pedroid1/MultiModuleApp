package com.pedroid.home.fragment

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pedroid.domain.model.Task
import com.pedroid.feature.home.R
import com.pedroid.feature.home.databinding.FragmentAddTaskDialogListDialogBinding

class AddTaskDialogFragment : BottomSheetDialogFragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity())[HomeViewModel::class.java] }
    private lateinit var binding: FragmentAddTaskDialogListDialogBinding
    private val args: AddTaskDialogFragmentArgs by navArgs()
    private val task by lazy {
        args.task
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
                        navigateToHomeScreen()
                    }
                }
            }
        }
    }

    private fun navigateToHomeScreen() {
        val action = AddTaskDialogFragmentDirections.actionAddTaskDialogFragmentToHomeScreenFragment()
        findNavController().navigate(action)
    }

    private fun setupListener() {
        binding.addBtn.setOnClickListener {
            val title = binding.taskTitleEdt.text.toString()
            val description = binding.taskDescriptionEdt.text.toString()
            task?.let {
                val taskCopy = it.copy(title = title, description = description)
                if(task != taskCopy) {
                    viewModel.insertTask(taskCopy)
                } else {
                    navigateToHomeScreen()
                }
            } ?: viewModel.insertTask(Task(title = title, description = description))
        }
    }
}