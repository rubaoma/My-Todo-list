package com.rubdev.mytodolist.datasource.repository

import com.rubdev.mytodolist.model.Task

interface TaskRepository {
    suspend fun insert(task: Task)
    suspend fun delete(task: Task)
}