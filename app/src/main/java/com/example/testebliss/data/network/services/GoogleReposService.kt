package com.example.testebliss.data.network.services

import com.example.testebliss.models.GoogleRepos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleReposService {

    @GET("users/{username}/repos")
    suspend fun getGoogleRepos(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<List<GoogleRepos>>
}