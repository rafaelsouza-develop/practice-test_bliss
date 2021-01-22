package com.example.testebliss.repository

import com.example.testebliss.data.network.Result
import com.example.testebliss.models.EmojiList

interface EmojiRepository {

    suspend fun getEmojis() : Result<EmojiList>

}