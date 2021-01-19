package com.example.testebliss.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "emoji")
class Emoji(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String? = null,
    var url: String? = null
)