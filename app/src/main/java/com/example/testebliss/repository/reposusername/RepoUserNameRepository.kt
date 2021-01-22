package com.example.testebliss.repository.reposusername

import com.example.testebliss.data.network.Result
import com.example.testebliss.models.RepoUserName

interface RepoUserNameRepository {

    suspend fun getRepoByUserName(username: String) : Result<RepoUserName>
}