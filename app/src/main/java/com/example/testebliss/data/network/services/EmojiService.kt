package com.example.testebliss.data.network.services

import com.example.testebliss.models.Emoji
import retrofit2.Response
import retrofit2.http.GET

interface EmojiService {

    @GET("emojis")
    suspend fun getEmojis(): Response<Map<String, String>>


}