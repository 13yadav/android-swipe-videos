package com.strangecoder.videostreaming

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.strangecoder.videostreaming.network.model.Video

class ViewPagerVideoAdapter(private val videos: List<Video>) :
    RecyclerView.Adapter<ViewPagerVideoAdapter.VideoViewHolder>() {

    val mediaPlayer = MediaPlayer()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.viewpager_video_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val currentVideo = videos[position]
        holder.itemView.findViewById<VideoView>(R.id.videoView).apply {
            getHolder().addCallback(object : SurfaceHolder.Callback {
                override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
                    mediaPlayer.apply {
                        setDataSource(currentVideo.video_url)
                        setDisplay(surfaceHolder)
                        prepareAsync()
                    }
                }

                override fun surfaceChanged(
                    holder: SurfaceHolder,
                    format: Int,
                    width: Int,
                    height: Int
                ) {
                }

                override fun surfaceDestroyed(holder: SurfaceHolder) {}
            })
        }
        mediaPlayer.setOnPreparedListener { mediaPlayer.start() }
    }

    override fun onViewAttachedToWindow(holder: VideoViewHolder) {
        super.onViewAttachedToWindow(holder)
        mediaPlayer.start()
    }

    override fun onViewDetachedFromWindow(holder: VideoViewHolder) {
        super.onViewDetachedFromWindow(holder)
        mediaPlayer.reset()
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}