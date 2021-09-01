package com.rubdev.mytodolist.di

import com.rubdev.mytodolist.datasource.repository.TaskRepositoryImpl
import com.rubdev.mytodolist.datasource.repository.TaskRepository
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {

    provideTaskRepository(this)
}

    fun provideTaskRepository(module: Module) = module.single {
        TaskRepositoryImpl(get())
    } bind TaskRepository::class
