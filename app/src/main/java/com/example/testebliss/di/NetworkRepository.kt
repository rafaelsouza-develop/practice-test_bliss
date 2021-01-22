package com.example.testebliss.di

import com.example.testebliss.BuildConfig
import com.example.testebliss.data.network.EmojiConverterFactory
import com.example.testebliss.data.network.RequestInterceptor
import com.example.testebliss.data.network.services.EmojiService
import com.example.testebliss.data.network.services.GoogleReposService
import com.example.testebliss.models.EmojiList
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(EmojiList::class.java, EmojiConverterFactory())

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(RequestInterceptor().setupOkHttp().build())
            .build()
    }

    single { get<Retrofit>().create(EmojiService::class.java) }
    single { get<Retrofit>().create(GoogleReposService::class.java) }
}