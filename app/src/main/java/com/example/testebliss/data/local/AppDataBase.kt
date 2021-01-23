package com.example.testebliss.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testebliss.models.Emoji
import com.example.testebliss.models.RepoUserName

@Database(version = 2, entities = [Emoji::class, RepoUserName::class])
abstract class AppDataBase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao
    abstract fun reposDao(): ReposDao
}