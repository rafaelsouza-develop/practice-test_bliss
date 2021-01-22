package com.example.testebliss.data.network

import com.example.testebliss.models.Emoji
import com.example.testebliss.models.EmojiList
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.squareup.moshi.FromJson
import org.json.JSONObject
import retrofit2.Response
import java.lang.reflect.Type

class EmojiConverterFactory : JsonDeserializer<EmojiList> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): EmojiList {
        val jsonObject = JSONObject(json.toString())
        val keys = jsonObject.keys()
        var emojiList = mutableListOf<Emoji>()
        while (keys.hasNext()) {
            val key = keys.next()
            emojiList.add(
                Emoji(
                    name = key,
                    url = jsonObject[key].toString()
                )
            )
        }
        return EmojiList(emojiList)
    }
}
