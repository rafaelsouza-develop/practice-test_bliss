package com.example.testebliss.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repos_user_name")
data class RepoUserName(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @SerializedName("login")
    var login: String,

    @SerializedName("avatar_url")
    var avatarUrl: String
)