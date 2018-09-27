package com.artto.fastrepost.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.fastrepost.R
import com.artto.fastrepost.di.ApplicationLoader
import com.artto.fastrepost.di.module.VideoItemModule
import com.artto.fastrepost.presentation.presenter.VideoItemPresenter
import com.artto.fastrepost.presentation.view.VideoItemView
import kotlinx.android.synthetic.main.item_content_video.*

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
    fun providePresenter(): VideoItemPresenter =
            ApplicationLoader
                    .applicationComponent
                    .videoItemComponent(VideoItemModule())
                    .videoItemPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.item_content_video, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        video_view_repost_content.apply {
            setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN)
                    if (isPlaying) {
                        showIcon(PLAY_ICON)
                        pause()
                    } else {
                        showIcon(PAUSE_ICON)
                        image_view_repost_video_pause.startAnimation(AnimationUtils.loadAnimation(context, R.anim.animation_video_control_hide))
                        start()
                    }
                false
            }
        }
    }

    private fun showIcon(icon: Int) {
        image_view_repost_video_play?.visibility = if (icon == PLAY_ICON) View.VISIBLE else View.GONE
        image_view_repost_video_pause?.visibility = if (icon == PAUSE_ICON) View.VISIBLE else View.GONE
    }

    override fun showVideo() {
        with(video_view_repost_content) {
            visibility = View.VISIBLE
            setVideoURI(Uri.parse(arguments?.getString(KEY_VIDEO_URL)))
            seekTo(100)
            showIcon(PLAY_ICON)
        }
    }
}