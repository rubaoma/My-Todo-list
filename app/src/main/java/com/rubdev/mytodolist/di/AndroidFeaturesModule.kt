package com.rubdev.mytodolist.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.rubdev.mytodolist.datasource.database.AppDatabase
import org.koin.dsl.module

val roomDatabaseModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "task_database" )
            .addMigrations().setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING).build()
    }


}