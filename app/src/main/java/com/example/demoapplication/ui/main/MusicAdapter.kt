package com.example.demoapplication.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapplication.R
import com.example.demoapplication.databinding.ItemMusicBinding
import com.example.demoapplication.network.data.response.music.Music

class MusicAdapter(private val context : Context,
                   private val musicResponseList: List<Music>): RecyclerView.Adapter<MusicAdapter.ViewHolder>() {
    private lateinit var binding : ItemMusicBinding

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        binding.apply {
            tvTitle.text = musicResponseList[position].trackName
            tvAuthor.text = musicResponseList[position].artistName
            tvTrackId.text = musicResponseList[position].trackId.toString()

            Glide.with(context)
                .load(musicResponseList[position].imageUrl)
                .into(ivTrackImage)
        }
    }

    override fun getItemCount() = musicResponseList.size
}