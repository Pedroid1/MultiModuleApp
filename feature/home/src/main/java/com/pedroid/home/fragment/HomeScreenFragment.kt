package com.pedroid.home.fragment

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.pedroid.common.base.BaseFragment
import com.pedroid.domain.model.Task
import com.pedroid.feature.home.R
import com.pedroid.feature.home.databinding.FragmentHomeScreenBinding
import com.pedroid.home.fragment.adapter.EnumTaskListAdapterViewType
import com.pedroid.home.fragment.adapter.HomeAdapterEvent
import com.pedroid.home.fragment.adapter.TaskListAdapter
import com.pedroid.home.fragment.adapter.TaskListAdapterItem

class HomeScreenFragment : BaseFragment<FragmentHomeScreenBinding>(R.layout.fragment_home_screen),
    HomeAdapterEvent {

    private val viewModel by lazy { ViewModelProvider(requireActivity())[HomeViewModel::class.java] }
    private val homeAdapter = TaskListAdapter(this)

    override fun initialWork() {
        _binding.taskRecyclerView.adapter = homeAdapter
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(_binding.taskRecyclerView)

        _binding.floatingBtn.setOnClickListener {
            showAddTaskBottomSheet()
        }
    }

    private fun showAddTaskBottomSheet(task: Task? = null) {
        val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToAddTaskDialogFragment(task)
        findNavController().navigate(action)
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
        showAddTaskBottomSheet()
    }

    override fun editTask(task: Task) {
        showAddTaskBottomSheet(task)
    }

    override fun toggleChecked(task: Task) {
        val checked = !task.isChecked
        viewModel.updateTask(task.copy(isChecked = checked))
    }

    override fun detail(task: Task) {
        showDetailBottomSheet(task)
    }

    private fun showDetailBottomSheet(task: Task) {
        val action = HomeScreenFragmentDirections.actionHomeScreenFragmentToTaskDetailDialogFragment(task)
        findNavController().navigate(action)
    }

    private val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
        0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return when (EnumTaskListAdapterViewType.getEnumByOrdinal(viewHolder.itemViewType)) {
                EnumTaskListAdapterViewType.TASK -> {
                    val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                    makeMovementFlags(0, swipeFlags)
                }

                else -> makeMovementFlags(0, 0)
            }
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ) = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val pos = viewHolder.layoutPosition
            val item = (homeAdapter.currentList[pos] as TaskListAdapterItem.TaskItem).task
            viewModel.deleteTask(item)
            Snackbar.make(requireView(), "Tarefa deletada", Snackbar.LENGTH_LONG).apply {
                setAction("Desfazer") {
                    viewModel.insertTask(item)
                }
                show()
            }
        }
    }
}