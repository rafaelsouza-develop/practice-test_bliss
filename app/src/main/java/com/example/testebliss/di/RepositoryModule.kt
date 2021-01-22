package com.example.testebliss.di

import com.example.testebliss.repository.emoji.EmojiRepository
import com.example.testebliss.repository.emoji.EmojiRepositoryImpl
import com.example.testebliss.repository.googlerepos.GoogleReposRepository
import com.example.testebliss.repository.googlerepos.GoogleReposRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<EmojiRepository> { EmojiRepositoryImpl(get()) }

    factory<GoogleReposRepository> { GoogleReposRepositoryImpl(get()) }
}