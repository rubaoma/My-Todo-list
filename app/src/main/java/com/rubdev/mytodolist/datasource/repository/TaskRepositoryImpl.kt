package com.rubdev.mytodolist.datasource.repository

import androidx.annotation.WorkerThread
import com.rubdev.mytodolist.datasource.database.TasksDao
import com.rubdev.mytodolist.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val tasksDao: TasksDao
): TaskRepository {

    val allTasks: Flow<List<Task>> = tasksDao.getAllTasks()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(task: Task) {
        tasksDao.insert(task)
    }

    @WorkerThread
    override suspend fun delete(task: Task) {
        tasksDao.delete(task)
    }

}