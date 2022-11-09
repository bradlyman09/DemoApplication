package com.example.demoapplication.di.modules

import com.example.demoapplication.network.services.MusicService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun providesGson() = GsonBuilder().create()

    @Singleton
    @Provides
    fun providesOkHttpClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun providesMusicService(retrofit: Retrofit) =  retrofit.create(MusicService::class.java)
}