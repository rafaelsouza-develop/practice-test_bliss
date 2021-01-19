package com.example.testebliss

import android.app.Application
import android.content.Context
import com.example.testebliss.di.networkModule
import com.example.testebliss.di.repositoryModule
import com.example.testebliss.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class CoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()
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