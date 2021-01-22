package com.example.testebliss.models

import com.google.gson.annotations.SerializedName

data class GoogleRepos (

    var id: String,

    @SerializedName("full_name")
    var fullName: String,

    @SerializedName("private")
    var isPrivate: Boolean
)