package com.strangecoder.videostreaming

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.strangecoder.videostreaming.network.model.Video

class ViewPagerVideoAdapter(private val videos: List<Video>) :
    RecyclerView.Adapter<ViewPagerVideoAdapter.VideoViewHolder>() {

    private val mediaPlayer = MediaPlayer()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.viewpager_video_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val currentVideo = videos[position]
        holder.bindVideo(currentVideo)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindVideo(video: Video) {
            itemView.findViewById<VideoView>(R.id.videoView).apply {
                setVideoPath(video.video_url)
                setOnPreparedListener { mp ->
                    mp.start()
                    val videoRatio: Float = mp.videoWidth.toFloat() / mp.videoHeight.toFloat()
                    val screenRatio: Float = this.width.toFloat() / this.height.toFloat()
                    val scale = videoRatio / screenRatio
                    if (scale >= 1F) {
                        this.scaleX = scale
                    } else {
                        this.scaleY = scale
                    }
                }
                setOnCompletionListener {
                    it.start()
                }
            }
        }
    }
}