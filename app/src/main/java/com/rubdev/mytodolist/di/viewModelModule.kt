package com.rubdev.mytodolist.di

import com.rubdev.mytodolist.viewmodel.TaskViewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val viewModelModule = module {

    provideTaskViewModel(this)
}

fun provideTaskViewModel(module: Module) = module.single {
    TaskViewModel(get())
} bind TaskViewModel::class