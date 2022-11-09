package com.example.demoapplication.network.services

import com.example.demoapplication.network.data.response.music.MusicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MusicService {
    @GET("search")
    suspend fun searchMusic(@QueryMap queryMap : HashMap<String, String>) : Response<MusicResponse>
}