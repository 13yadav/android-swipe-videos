package com.strangecoder.videostreaming.network

import com.strangecoder.videostreaming.network.model.Video
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface VideoApi {

    @GET("VideoDummyApi/dummy_data.json")
    fun getPopularVideosAsync(): Deferred<List<Video>>
}