package com.example.testebliss.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class RequestInterceptor {

    fun logger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    fun setupOkHttp(): OkHttpClient.Builder {
       // credentialsDao = CredentialsDaoImpl(App4PetsApplication.context)
        val okHttp = OkHttpClient.Builder()
        okHttp.addInterceptor(logger())
        okHttp.addInterceptor { chain ->
            var newRequest = chain.request()
            newRequest = newRequest.newBuilder().addHeader(
                "Authorization",
                "Bearer "
            ).build()
            return@addInterceptor chain.proceed(newRequest)
        }
        return okHttp
    }
}