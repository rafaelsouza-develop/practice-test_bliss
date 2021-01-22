package com.example.testebliss.data.local

import androidx.room.*
import com.example.testebliss.models.Emoji

@Dao
interface EmojiDao {

    @Query("SELECT * FROM emoji")
    fun getAllEmojis(): List<Emoji>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmoji(emoji: Emoji)

    @Update
    fun updateEmoji(emoji: Emoji)

    @Delete
    fun deleteEmoji(emoji: Emoji)
}