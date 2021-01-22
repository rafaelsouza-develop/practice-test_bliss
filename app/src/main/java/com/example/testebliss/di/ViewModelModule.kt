package com.example.testebliss.di

import com.example.testebliss.modules.emojislist.EmojiListViewModel
import com.example.testebliss.modules.main.MainViewModel
import com.example.testebliss.util.DispatcherProvider
import org.koin.dsl.module

val viewModelModule = module {
    factory { DispatcherProvider() }
    factory { MainViewModel(get(), get()) }
    factory { EmojiListViewModel(get(), get()) }
}