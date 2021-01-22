package com.example.testebliss.data.network.services

import com.example.testebliss.models.EmojiList
import com.example.testebliss.models.RepoUserName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EmojiService {

    @GET("emojis")
    suspend fun getEmojis(): Response<EmojiList>

    @GET("users/{username}")
    suspend fun getRepoByUserName(@Path("username") username: String) : Response<RepoUserName>
}