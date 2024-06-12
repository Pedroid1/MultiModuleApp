package com.pedroid.home.fragment

import androidx.lifecycle.ViewModelProvider
import com.pedroid.common.base.BaseFragment
import com.pedroid.domain.model.Task
import com.pedroid.feature.home.R
import com.pedroid.feature.home.databinding.FragmentHomeScreenBinding
import com.pedroid.home.fragment.adapter.HomeAdapterEvent
import com.pedroid.home.fragment.adapter.TaskListAdapter
import com.pedroid.home.fragment.adapter.TaskListAdapterItem

class HomeScreenFragment : BaseFragment<FragmentHomeScreenBinding>(R.layout.fragment_home_screen),
    HomeAdapterEvent {

    private val viewModel by lazy { ViewModelProvider(requireActivity())[HomeViewModel::class.java] }
    private val homeAdapter = TaskListAdapter(this)

    override fun initialWork() {
        _binding.taskRecyclerView.adapter = homeAdapter

        _binding.floatingBtn.setOnClickListener {
            // TODO Open AddTaskScreen
            viewModel.insertTask(Task(title = "Teste", description = "Fazer compras de verdura"))
        }
    }

    override fun setupViewModel() {
        _binding.vm = viewModel
    }

    override fun setupObservers() {
        viewModel.homeUiState.observe(viewLifecycleOwner) { uiState ->
            uiState.data?.let { data ->
                homeAdapter.submitList(viewModel.generateHomeAdapterList(data))
            }
        }
    }

    override fun addTask() {
        // TODO Open AddTaskScreen
        viewModel.insertTask(Task(title = "Teste", description = "Fazer compras de verdura"))
    }

    override fun editTask(task: Task) {
        // TODO Open EditTaskScreen
    }

    override fun toggleChecked(task: Task) {
        val checked = !task.isChecked
        viewModel.insertTask(task.copy(isChecked = checked))
    }
}