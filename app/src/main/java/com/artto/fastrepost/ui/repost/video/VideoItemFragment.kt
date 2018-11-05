package com.artto.fastrepost.ui.repost.video

import android.os.Bundle
import android.support.v4.content.FileProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.fastrepost.R
import com.artto.fastrepost.ApplicationLoader
import com.artto.fastrepost.di.repost.video.VideoItemModule
import com.artto.fastrepost.ui.base.BaseFragment
import kotlinx.android.synthetic.main.item_content_video.*
import java.io.File

class VideoItemFragment : BaseFragment(), VideoItemView {

    companion object {
        private const val PLAY_ICON = 0
        private const val PAUSE_ICON = 1
        private const val KEY_VIDEO_URL = "video_url"

        fun newInstance(videoUrl: String) = VideoItemFragment().apply {
            arguments = Bundle().apply { putString(KEY_VIDEO_URL, videoUrl) }
        }
    }

    @InjectPresenter
    lateinit var presenter: VideoItemPresenter

    @ProvidePresenter
    fun providePresenter(): VideoItemPresenter = ApplicationLoader
            .applicationComponent
            .videoItemComponent(VideoItemModule())
            .videoItemPresenter()

    private var isPlaying = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.item_content_video, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        video_view_repost_content.setOnClickListener { presenter.onVideoPlayerClicked(isPlaying) }
    }

    private fun showIcon(icon: Int) {
        image_view_repost_video_play?.visibility = if (icon == PLAY_ICON) View.VISIBLE else View.GONE
        image_view_repost_video_pause?.visibility = if (icon == PAUSE_ICON) View.VISIBLE else View.GONE
    }

    override fun showVideo() {
        with(video_view_repost_content) {
            setVideoURI(FileProvider.getUriForFile(context,
                    "com.artto.fastrepost.fileprovider",
                    File(arguments?.getString(KEY_VIDEO_URL))))

            visibility = View.VISIBLE
            showIcon(PLAY_ICON)
            seekTo(100)
        }
    }

    override fun playVideo() {
        isPlaying = true
        showIcon(PAUSE_ICON)
        image_view_repost_video_pause.startAnimation(AnimationUtils.loadAnimation(context, R.anim.animation_video_control_hide))
        video_view_repost_content.start()
    }

    override fun pauseVideo() {
        isPlaying = false
        showIcon(PLAY_ICON)
        video_view_repost_content.pause()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isPlaying && !isVisibleToUser) pauseVideo()
        super.setUserVisibleHint(isVisibleToUser)
    }
}