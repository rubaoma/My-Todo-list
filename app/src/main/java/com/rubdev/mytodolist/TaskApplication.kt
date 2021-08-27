package com.rubdev.mytodolist

import android.app.Application
import com.rubdev.mytodolist.di.getKoinModuleList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TaskApplication : Application() {
    companion object {
        var context: Application? = null
    }
    override fun onCreate() {
        super.onCreate()
        context = this
        startKoin {
            androidContext(this@TaskApplication)
            modules(
                getKoinModuleList()
            )
        }
    }

//    val applicationScope = CoroutineScope(SupervisorJob())
//    val database by lazy { TaskRoomDatabase.getDataBase(this, applicationScope)}
//    val repository by lazy { TaskRepositoryImpl(database.taskDao()) }
}