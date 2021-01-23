package com.example.testebliss.data.network

import com.example.testebliss.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class RequestInterceptor {

    fun logger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    fun setupOkHttp(): OkHttpClient.Builder {
        val okHttp = OkHttpClient.Builder()
        okHttp.addInterceptor(logger())
        okHttp.addInterceptor { chain ->
            var newRequest = chain.request()
            newRequest = newRequest.newBuilder().addHeader(
                "authorization",
                "token ${BuildConfig.API_TOKEN}"
            ).build()
            return@addInterceptor chain.proceed(newRequest)
        }
        return okHttp
    }

}