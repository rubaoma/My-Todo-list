package com.rubdev.mytodolist

import android.app.Application
import com.rubdev.mytodolist.datasource.TaskRepository
import com.rubdev.mytodolist.datasource.TaskRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TaskApplication : Application() {



    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { TaskRoomDatabase.getDataBase(this, applicationScope)}
    val repository by lazy { TaskRepository(database.taskDao()) }
}