package com.rubdev.mytodolist.di

import com.rubdev.mytodolist.datasource.database.AppDatabase
import org.koin.dsl.module

val daoModule = module {
    single { get<AppDatabase>().getTaskDao() }
}