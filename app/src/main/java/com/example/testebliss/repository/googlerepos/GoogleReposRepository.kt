package com.example.testebliss.repository.googlerepos

import com.example.testebliss.data.network.Result
import com.example.testebliss.models.GoogleRepos

interface GoogleReposRepository {

    suspend fun getGoogleRepos(
        googleRepo: String,
        page: Int = 1,
        size: Int = 10
    ): Result<List<GoogleRepos>>
}