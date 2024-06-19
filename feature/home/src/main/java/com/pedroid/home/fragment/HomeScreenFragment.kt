package com.pedroid.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.pedroid.common.base.BaseFragment
import com.pedroid.feature.home.R
import com.pedroid.feature.home.databinding.FragmentHomeScreenBinding
import com.pedroid.home.fragment.adapter.EnumTaskListAdapterViewType
import com.pedroid.home.fragment.adapter.HomeAdapterEvent
import com.pedroid.home.fragment.adapter.TaskListAdapter
import com.pedroid.home.fragment.adapter.TaskListAdapterItem
import com.pedroid.model.Task
import kotlinx.coroutines.launch

class HomeScreenFragment : Fragment(), HomeAdapterEvent {

    private val viewModel by lazy { ViewModelProvider(requireActivity())[HomeViewModel::class.java] }
    private val homeAdapter = TaskListAdapter(this)
    private lateinit var _binding: FragmentHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("OnCreateTest", "OnCreate")
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect { result ->
                    result.data?.let { data ->
                        homeAdapter.submitList(viewModel.generateHomeAdapterList(data))
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialWork()
    }

    fun initialWork() {
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