package com.artto.fastrepost.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class RepostFragment : BaseFragment(), RepostView {

    @InjectPresenter
    lateinit var presenter: RepostPresenter

    @ProvidePresenter
    fun providePresenter() =
            ApplicationLoader
                    .applicationComponent
                    .repostComponent(RepostModule())
                    .repostPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_repost, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_repost_save.setOnClickListener { presenter.onSaveClicked() }
        button_repost_repost.setOnClickListener { presenter.onRepostClicked() }
        image_view_repost_content.adjustViewBounds = true
    }

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_LONG).show()

    override fun setImage(url: String) {
        Glide.with(image_view_repost_content)
                .load(url)
                .into(image_view_repost_content)
    }

    override fun setCaption(text: String) = edit_text_repost_caption.setText(text)

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }
}