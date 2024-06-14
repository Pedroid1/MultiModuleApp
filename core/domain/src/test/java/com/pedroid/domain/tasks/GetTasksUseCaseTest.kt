package com.pedroid.domain.tasks

import com.google.common.truth.Truth.assertThat
import com.pedroid.data.model.TaskEntity
import com.pedroid.data.repositories.tasks.TasksRepository
import com.pedroid.domain.model.Task
import com.pedroid.domain.model.toEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetTasksUseCaseTest {

    private val repository: TasksRepository = mockk(relaxed = true)
    private val useCase = GetTasksUseCaseImpl(repository)

    @Test
    fun `getTasks returns sorted tasks by isChecked and Title`() = runTest {
        val tasksToReturn = mutableListOf<TaskEntity>()
        ('a'..'z').forEachIndexed { index, c ->
            tasksToReturn.add(
                TaskEntity(
                    uid = index,
                    title = c.toString(),
                    description = "",
                    isChecked = index % 2 == 0
                )
            )
        }
        tasksToReturn.shuffle()

        every { repository.getTasks() } returns flowOf(tasksToReturn)

        val tasksReturned = useCase.getTasks().first()

        val (uncheckedTasks, checkedTasks) = tasksReturned.partition { !it.isChecked }
        for(i in 0..uncheckedTasks.size - 2) {
            assertThat(uncheckedTasks[i].title).isLessThan(uncheckedTasks[i + 1].title)
        }
        for(i in 0..checkedTasks.size - 2) {
            assertThat(checkedTasks[i].title).isLessThan(checkedTasks[i + 1].title)
        }
        assertThat(uncheckedTasks + checkedTasks).isEqualTo(tasksReturned)
    }

    @Test
    fun `insertTask calls repository insertTask`() {
        val task = Task(1, "title", "")
        every { repository.insertTask(any()) } returns Unit

        useCase.insertTask(task)

        verify { repository.insertTask(task.toEntity()) }
    }

    @Test
    fun `deleteTask calls repository deleteTask`() {
        val task = Task(1, "title", "")
        every { repository.deleteTask(any()) } returns Unit

        useCase.deleteTask(task)

        verify { repository.deleteTask(task.toEntity()) }
    }

    @Test
    fun `updateTask calls repository`() {
        val task = Task(1, "title", "")
        every { repository.updateTask(any()) } returns Unit

        useCase.updateTask(task)

        verify { repository.updateTask(task.toEntity()) }
    }

}