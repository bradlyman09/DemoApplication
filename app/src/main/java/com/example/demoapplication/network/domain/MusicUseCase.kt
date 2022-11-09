package com.example.demoapplication.network.domain

import com.example.demoapplication.network.ResultWrapper
import com.example.demoapplication.network.data.response.music.Music
import com.example.demoapplication.network.data.response.music.MusicResponse
import com.example.demoapplication.network.handleApi
import com.example.demoapplication.network.services.MusicService
import javax.inject.Inject

class MusicUseCase @Inject constructor(
    private val musicService: MusicService
) {

    suspend operator fun invoke(queryMap : HashMap<String, String>) : ResultWrapper<MusicResponse>{
        return handleApi {
            musicService.searchMusic(queryMap)
        }
    }
}