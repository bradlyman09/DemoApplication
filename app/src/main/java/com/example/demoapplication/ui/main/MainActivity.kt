package com.example.demoapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.demoapplication.databinding.ActivityMainBinding
import com.example.demoapplication.network.data.response.music.Music
import com.example.demoapplication.network.data.response.music.MusicResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    private val musicList = mutableListOf<Music>()
    private val musicAdapter : MusicAdapter by lazy {
        MusicAdapter(this, musicList)
    }

    private lateinit var countDownTimer : CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.searchMusic()

        initViews()
        setupObservables()
    }

    private fun initViews(){
        binding.rvMusicList.apply {
            adapter = musicAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }

        binding.srMusicList.setOnRefreshListener {
            //for demo purposes we will clear the list so we can see updates
            musicList.clear()
            musicAdapter.notifyDataSetChanged()
            countDownTimer.start()
        }

        countDownTimer = object : CountDownTimer(3000, 1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                viewModel.searchMusic()
                binding.srMusicList.isRefreshing = false
            }
        }
    }

    private fun setupObservables(){
        viewModel.musicLiveData.observe(this){
            musicList.clear()
            musicList.addAll(it)
            musicAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}