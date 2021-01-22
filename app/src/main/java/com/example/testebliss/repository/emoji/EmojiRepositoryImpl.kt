package com.example.testebliss.repository.emoji

import com.example.testebliss.data.network.Result
import com.example.testebliss.data.network.services.EmojiService
import com.example.testebliss.models.EmojiList
import com.example.testebliss.repository.emoji.EmojiRepository

class EmojiRepositoryImpl(private val emojiService: EmojiService) :
    EmojiRepository {

    override suspend fun getEmojis(): Result<EmojiList> {
        val response = emojiService.getEmojis()
        if(response.isSuccessful){
            return Result.Success(response.body()!!)
        }
        return Result.Failure(Throwable("Error ${response.errorBody()} ${response.message()} "))
    }
}