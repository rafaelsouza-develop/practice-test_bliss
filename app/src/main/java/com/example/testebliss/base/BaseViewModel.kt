package com.example.testebliss.base

import androidx.lifecycle.ViewModel
import com.example.testebliss.util.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.KoinComponent

open class BaseViewModel(dispatcherProvider: DispatcherProvider) : ViewModel(), KoinComponent {

    private val viewModelJob = SupervisorJob()
    protected val scope = CoroutineScope(dispatcherProvider.io + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}