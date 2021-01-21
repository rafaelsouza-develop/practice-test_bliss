package com.example.testebliss.repository

import com.example.testebliss.data.network.Result
import com.example.testebliss.data.network.services.EmojiService
import com.example.testebliss.models.Emoji

class EmojiRepositoryImpl(private val emojiService: EmojiService) : EmojiRepository {

    override suspend fun getEmojis(): Result<List<Emoji>> {
        val response = emojiService.getEmojis()
        if(response.isSuccessful){
            val listEmojis = arrayListOf<Emoji>()
            for ((key, value) in response.body()!!.entries) {
                listEmojis.add(Emoji(0, key, value))
            }
            return Result.Success(listEmojis)
        }
        return Result.Failure(Throwable("Error ${response.errorBody()} ${response.message()} "))
    }
}