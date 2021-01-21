package com.example.testebliss.di

import com.example.testebliss.repository.EmojiRepository
import com.example.testebliss.repository.EmojiRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<EmojiRepository> { EmojiRepositoryImpl(get()) }
}