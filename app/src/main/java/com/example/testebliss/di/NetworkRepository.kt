package com.example.testebliss.di

import com.example.testebliss.BuildConfig
import com.example.testebliss.data.network.EmojiAdapter
import com.example.testebliss.data.network.RequestInterceptor
import com.example.testebliss.data.network.services.EmojiService
import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {

        val moshi = Moshi.Builder()
            .add(EmojiAdapter())
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(RequestInterceptor().setupOkHttp().build())
            .build()
    }

    single { get<Retrofit>().create(EmojiService::class.java) }
}