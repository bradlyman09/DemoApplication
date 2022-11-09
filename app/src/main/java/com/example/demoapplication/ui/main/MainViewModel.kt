package com.example.demoapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapplication.network.ResultWrapper
import com.example.demoapplication.network.data.response.music.Music
import com.example.demoapplication.network.domain.MusicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val musicUseCase: MusicUseCase): ViewModel(){
    private val _musicListLiveData = MutableLiveData<List<Music>>()
    val musicLiveData : LiveData<List<Music>> = _musicListLiveData

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse : LiveData<String> = _errorResponse

    fun searchMusic(){
        viewModelScope.launch {
            val queryMap = HashMap<String, String>()
            queryMap["term"] = "jack+johnson"
            queryMap["limit"] = "10"

            when(val response = musicUseCase(queryMap)){
                is ResultWrapper.ApiSuccess -> _musicListLiveData.postValue(response.body.results)
                is ResultWrapper.ApiError -> _errorResponse.postValue(response.errorMessage?.errorMessage ?: "Unknown error")
                else -> {}
            }


        }
    }
}