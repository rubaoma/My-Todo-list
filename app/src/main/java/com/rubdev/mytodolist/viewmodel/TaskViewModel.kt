package com.rubdev.mytodolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rubdev.mytodolist.datasource.repository.TaskRepositoryImpl
import com.rubdev.mytodolist.model.Task
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repositoryImpl: TaskRepositoryImpl
) : ViewModel() {

    val allTasks: LiveData<List<Task>> = repositoryImpl.allTasks.asLiveData()

    fun insert(task: Task) = viewModelScope.launch {
        repositoryImpl.insert(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repositoryImpl.delete(task)
    }

}
