package com.example.testebliss.repository

import com.example.testebliss.data.network.Result
import com.example.testebliss.data.network.services.EmojiService
import com.example.testebliss.models.EmojiList

class EmojiRepositoryImpl(private val emojiService: EmojiService) : EmojiRepository {

    override suspend fun getEmojis(): Result<EmojiList> {
        val response = emojiService.getEmojis()
        if(response.isSuccessful){
            return Result.Success(response.body()!!)
        }
        return Result.Failure(Throwable("Error ${response.errorBody()} ${response.message()} "))
    }
}