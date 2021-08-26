package com.rubdev.mytodolist.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rubdev.mytodolist.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun taskDao(): TasksDao

    private class TaskDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val taskDao = database.taskDao()

                    taskDao.deleteAll()

                    val task = Task(
                        title = "Aprendendo sobre Tudo",
                        hours = "16:54",
                        date = "15/09/2021"
                    )
                    taskDao.insert(task)
                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: TaskRoomDatabase? = null
        fun getDataBase(
            context: Context,
            scope: CoroutineScope
        ): TaskRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java,
                    "task_database"
                ).addCallback(TaskDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}