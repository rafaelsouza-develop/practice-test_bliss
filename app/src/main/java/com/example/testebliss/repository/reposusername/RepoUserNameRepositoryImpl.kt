package com.example.testebliss.repository.reposusername

import com.example.testebliss.data.network.Result
import com.example.testebliss.data.network.services.EmojiService
import com.example.testebliss.models.RepoUserName

class RepoUserNameRepositoryImpl(private val emojiService: EmojiService) : RepoUserNameRepository {

    override suspend fun getRepoByUserName(username: String): Result<RepoUserName> {
        val response = emojiService.getRepoByUserName(username)
        if (response.isSuccessful) {
            return Result.Success(response.body()!!)
        }
        return Result.Failure(Throwable("Error ${response.errorBody()} ${response.message()} "))
    }
}