package com.strangecoder.videostreaming

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.strangecoder.videostreaming.databinding.ActivityMainBinding
import com.strangecoder.videostreaming.network.RetrofitService.retrofitService
import com.strangecoder.videostreaming.network.model.Video
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val videoList = MutableLiveData<List<Video>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val result = retrofitService.getPopularVideosAsync().await()
            videoList.value = result
        }

        videoList.observe(this, {
            val videoAdapter = ViewPagerVideoAdapter(it)
            binding.videoViewPager.adapter = videoAdapter
        })
    }
}