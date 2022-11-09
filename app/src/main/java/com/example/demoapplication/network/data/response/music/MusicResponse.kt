package com.example.demoapplication.network.data.response.music

import com.google.gson.annotations.SerializedName

data class MusicResponse(
    @SerializedName("resultCount")
    val resultCount : Int,

    @SerializedName("results")
    val results : List<Music>
)
