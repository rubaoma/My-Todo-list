package com.rubdev.mytodolist.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rubdev.mytodolist.model.Task
import kotlin.reflect.KClass

@Database(
    entities = [
        Task::class
    ],
    version = 1,
    exportSchema = true
)
@DatabaseEntities(
    [
        Task::class
    ]
)

abstract class AppDatabase : RoomDatabase(){
    abstract fun getTaskDao(): TasksDao
}

annotation class DatabaseEntities(val entities: Array<KClass<*>>)
