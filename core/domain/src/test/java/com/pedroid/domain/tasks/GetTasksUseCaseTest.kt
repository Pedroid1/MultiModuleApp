package com.pedroid.domain.tasks

import com.google.common.truth.Truth.assertThat
import com.pedroid.data.repositories.tasks.TasksRepository
import com.pedroid.model.Task
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetTasksUseCaseTest {

    private val mockRepository: TasksRepository = mockk(relaxed = true)
    private val useCase: GetTasksUseCase = GetTasksUseCaseImpl(mockRepository)

    @Test
    fun `given getTasks returns sorted tasks by isChecked and Title when getTasks is called then repository getTasks should return sorted tasks`() =
        runTest {
            val tasksToReturn = mutableListOf<Task>()
            ('a'..'z').forEachIndexed { index, c ->
                tasksToReturn.add(
                    Task(
                        uid = index,
                        title = c.toString(),
                        description = "",
                        isChecked = index % 2 == 0
                    )
                )
            }
            tasksToReturn.shuffle()

            every { mockRepository.getTasks() } returns flowOf(tasksToReturn)

            val tasksReturned = useCase.getTasks().first()

            val (uncheckedTasks, checkedTasks) = tasksReturned.partition { !it.isChecked }
            for (i in 0 until uncheckedTasks.size - 1) {
                assertThat(uncheckedTasks[i].title).isLessThan(uncheckedTasks[i + 1].title)
            }
            for (i in 0 until checkedTasks.size - 1) {
                assertThat(checkedTasks[i].title).isLessThan(checkedTasks[i + 1].title)
            }
            assertThat(uncheckedTasks + checkedTasks).isEqualTo(tasksReturned)
        }

    @Test
    fun `given insertTask is called with a task when insertTask is called then repository insertTask should be called with the task converted to entity`() {
        val task = Task(1, "title", "")
        every { mockRepository.insertTask(any()) } returns Unit

        useCase.insertTask(task)

        verify { mockRepository.insertTask(task) }
    }

    @Test
    fun `given deleteTask is called with a task when deleteTask is called then repository deleteTask should be called with the task converted to entity`() {
        val task = Task(1, "title", "")
        every { mockRepository.deleteTask(any()) } returns Unit

        useCase.deleteTask(task)

        verify { mockRepository.deleteTask(task) }
    }

    @Test
    fun `given updateTask is called with a task when updateTask is called then repository updateTask should be called with the task converted to entity`() {
        val task = Task(1, "title", "")
        every { mockRepository.updateTask(any()) } returns Unit

        useCase.updateTask(task)

        verify { mockRepository.updateTask(task) }
    }
}
