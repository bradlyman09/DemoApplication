package com.example.demoapplication.network.data.response.music

import com.google.gson.annotations.SerializedName

data class Music(
    @SerializedName("trackId")
    val trackId : Long,

    @SerializedName("trackName")
    val trackName : String,

    @SerializedName("artistName")
    val artistName : String,

    @SerializedName("artworkUrl100")
    val imageUrl : String
)
