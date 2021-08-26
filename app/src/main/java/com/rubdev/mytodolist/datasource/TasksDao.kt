package com.rubdev.mytodolist.datasource

import androidx.room.*
import com.rubdev.mytodolist.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {
    @Query("SELECT * FROM TASKS_TABLE")
    fun getAllTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("DELETE FROM TASKS_TABLE WHERE id = :taskId")
    suspend fun deleteTask(taskId: String)

    @Delete
    suspend fun delete(task: Task)

    @Query("DELETE FROM TASKS_TABLE")
    suspend fun deleteAll()

    @Query("SELECT * FROM TASKS_TABLE WHERE id = :taskId")
    suspend fun findTaskById (taskId: Int): Task


}