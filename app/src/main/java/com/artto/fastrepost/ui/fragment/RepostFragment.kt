package com.artto.fastrepost.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.MediaController
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.fastrepost.R
import com.artto.fastrepost.di.ApplicationLoader
import com.artto.fastrepost.di.module.RepostModule
import com.artto.fastrepost.presentation.presenter.RepostPresenter
import com.artto.fastrepost.presentation.view.RepostView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_repost.*
import kotlinx.android.synthetic.main.fragment_repost.view.*

class RepostFragment : BaseFragment(), RepostView {

    companion object {
        const val PLAY_ICON = 0
        const val PAUSE_ICON = 1
    }

    @InjectPresenter
    lateinit var presenter: RepostPresenter

    @ProvidePresenter
    fun providePresenter() =
            ApplicationLoader
                    .applicationComponent
                    .repostComponent(RepostModule())
                    .repostPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, p2: Bundle?): View? =
            inflater.inflate(R.layout.fragment_repost, container, false)

    override fun onViewCreated(p0: View, p1: Bundle?) {
        super.onViewCreated(p0, p1)
        button_repost_save.setOnClickListener { presenter.onSaveClicked() }
        button_repost_repost.setOnClickListener { presenter.onRepostClicked() }

        video_view_repost_content.apply {
            setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN)
                    if (isPlaying) {
                        showIcon(PLAY_ICON)
                        pause()
                    } else {
                        showIcon(PAUSE_ICON)
                        view?.image_view_repost_video_pause?.startAnimation(AnimationUtils.loadAnimation(context, R.anim.animation_video_control_hide))
                        start()
                    }
                false
            }
        }
    }

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_LONG).show()

    override fun setImage(url: String) {
        video_view_repost_content.visibility = View.GONE
        image_view_repost_content.visibility = View.VISIBLE
        Glide.with(image_view_repost_content)
                .load(url)
                .into(image_view_repost_content)
    }

    override fun setVideo(url: String) {
        image_view_repost_content.visibility = View.GONE
        with(video_view_repost_content) {
            visibility = View.VISIBLE
            setVideoURI(Uri.parse(url))
            seekTo(100)
            showIcon(PLAY_ICON)
        }
    }

    private fun showIcon(icon: Int) = view?.let {
        image_view_repost_video_play?.visibility = if (icon == PLAY_ICON) View.VISIBLE else View.GONE
        image_view_repost_video_pause?.visibility = if (icon == PAUSE_ICON) View.VISIBLE else View.GONE
    }

    override fun setCaption(text: String) = edit_text_repost_caption.setText(text)

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }
}