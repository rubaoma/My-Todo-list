package com.rubdev.mytodolist.viewmodel

import androidx.lifecycle.*
import com.rubdev.mytodolist.datasource.TaskRepositoryImpl
import com.rubdev.mytodolist.model.Task
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TaskViewModel(private val repositoryImpl: TaskRepositoryImpl): ViewModel() {

    val allTasks: LiveData<List<Task>> = repositoryImpl.allTasks.asLiveData()

    fun insert(task: Task) = viewModelScope.launch {
        repositoryImpl.insert(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repositoryImpl.delete(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        repositoryImpl.update(task)
    }

    fun findTaskById(taskId: Int) = viewModelScope.launch {
        repositoryImpl.findTaskById(taskId)
    }

}

class TaskViewModelFactory(private val repositoryImpl: TaskRepositoryImpl): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(TaskViewModel::class.java)){
           return TaskViewModel(repositoryImpl) as T
       }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}