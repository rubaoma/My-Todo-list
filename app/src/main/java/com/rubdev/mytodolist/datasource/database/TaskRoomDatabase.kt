package com.rubdev.mytodolist.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rubdev.mytodolist.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun taskDao(): TasksDao

}