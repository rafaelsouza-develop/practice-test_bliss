package com.example.testebliss.models

import com.google.gson.annotations.SerializedName

data class RepoUserName (
    var id: String,

    @SerializedName("lgin")
    var login: String,

    @SerializedName("avatar_url")
    var avatarUrl: String
)