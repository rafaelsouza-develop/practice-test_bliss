package com.example.testebliss.di

import com.example.testebliss.data.network.RequestInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(RequestInterceptor().setupOkHttp().build())
            .build()
    }
}