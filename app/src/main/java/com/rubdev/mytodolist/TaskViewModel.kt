package com.rubdev.mytodolist

import androidx.lifecycle.*
import com.rubdev.mytodolist.datasource.TaskRepository
import com.rubdev.mytodolist.model.Task
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TaskViewModel(private val repository: TaskRepository): ViewModel() {

    val allTasks: LiveData<List<Task>> = repository.allTasks.asLiveData()

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        repository.update(task)
    }

    fun findTaskById(taskId: Int) = viewModelScope.launch {
        repository.findTaskById(taskId)
    }

}

class TaskViewModelFactory(private val repository: TaskRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(TaskViewModel::class.java)){
           return TaskViewModel(repository) as T
       }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}