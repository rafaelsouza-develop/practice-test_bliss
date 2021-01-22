package com.example.testebliss.di

import com.example.testebliss.repository.emoji.EmojiRepository
import com.example.testebliss.repository.emoji.EmojiRepositoryImpl
import com.example.testebliss.repository.googlerepos.GoogleReposRepository
import com.example.testebliss.repository.googlerepos.GoogleReposRepositoryImpl
import com.example.testebliss.repository.reposusername.RepoUserNameRepository
import com.example.testebliss.repository.reposusername.RepoUserNameRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<EmojiRepository> { EmojiRepositoryImpl(get()) }

    factory<GoogleReposRepository> { GoogleReposRepositoryImpl(get()) }
    factory<RepoUserNameRepository> { RepoUserNameRepositoryImpl(get()) }
}