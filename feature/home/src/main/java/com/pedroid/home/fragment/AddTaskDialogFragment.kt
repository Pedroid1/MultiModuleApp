package com.pedroid.home.fragment

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pedroid.feature.home.databinding.FragmentAddTaskDialogListDialogBinding

class AddTaskDialogFragment : BottomSheetDialogFragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity())[HomeViewModel::class.java] }
    private lateinit var binding: FragmentAddTaskDialogListDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskDialogListDialogBinding.inflate(inflater, container, false)
        return binding.root

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
                        val action = AddTaskDialogFragmentDirections.actionAddTaskDialogFragmentToHomeScreenFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }

    private fun setupListener() {
        binding.addBtn.setOnClickListener {
            viewModel.insertTask(
                binding.taskTitleEdt.text.toString(),
                binding.taskDescriptionEdt.text.toString()
            )
        }
    }
}