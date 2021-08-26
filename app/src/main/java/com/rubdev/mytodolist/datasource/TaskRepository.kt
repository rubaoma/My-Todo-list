package com.rubdev.mytodolist.datasource

import androidx.annotation.WorkerThread
import com.rubdev.mytodolist.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val tasksDao: TasksDao) {

    val allTasks: Flow<List<Task>> = tasksDao.getAllTasks()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(task: Task){
        tasksDao.insert(task)
    }

    @WorkerThread
    suspend fun update(task: Task){
        tasksDao.updateTask(task)
    }

    @WorkerThread
    suspend fun delete(task: Task){
        tasksDao.delete(task)
    }

    @WorkerThread
    suspend fun findTaskById (taskId: Int) {
        tasksDao.findTaskById(taskId)
    }
}