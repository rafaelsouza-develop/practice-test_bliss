package com.example.testebliss.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testebliss.models.Emoji

@Database(version = 1, entities = [Emoji::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao
}