package com.example.testebliss

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.testebliss.data.local.AppDataBase
import com.example.testebliss.di.networkModule
import com.example.testebliss.di.repositoryModule
import com.example.testebliss.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class CoreApplication : Application() {

    companion object {
        var database: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            baseContext,
            AppDataBase::class.java,
            "database.db"
        ).fallbackToDestructiveMigration().build()
        setupApp(baseContext)


    }

    private fun setupApp(context: Context) {
        startKoin {
            androidContext(context)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }

    }
}