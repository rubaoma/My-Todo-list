package com.rubdev.mytodolist.datasource.database

import com.rubdev.mytodolist.model.Task

interface TaskRepository {
    suspend fun insert(task: Task)
    suspend fun update(task: Task)
    suspend fun delete(task: Task)
    suspend fun findTaskById(taskId: Int)

}