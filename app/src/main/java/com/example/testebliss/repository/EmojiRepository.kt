package com.example.testebliss.repository

import com.example.testebliss.data.network.Result
import com.example.testebliss.models.Emoji

interface EmojiRepository {

    suspend fun getEmojis() : Result<List<Emoji>>
}