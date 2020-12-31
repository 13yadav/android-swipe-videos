package com.strangecoder.videostreaming.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.strangecoder.videostreaming.network.ApiEndpoints.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    val retrofitService: VideoApi by lazy {
        retrofit.create(VideoApi::class.java)
    }
}