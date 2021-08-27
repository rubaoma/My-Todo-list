package com.rubdev.mytodolist.di

fun getKoinModuleList() = listOf(
    roomDatabaseModule,
    daoModule,
    repositoryModule,
    viewModelModule
)