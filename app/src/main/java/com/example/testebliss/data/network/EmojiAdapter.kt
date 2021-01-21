package com.example.testebliss.data.network

import com.example.testebliss.models.Emoji
import com.squareup.moshi.FromJson
import retrofit2.Response

class EmojiAdapter {

    @FromJson
    fun fromJson(emoji: Response<Map<String, String>>): Emoji {
        val name = emoji
        val url = emoji
        return  Emoji(0, "", "")
    }


}