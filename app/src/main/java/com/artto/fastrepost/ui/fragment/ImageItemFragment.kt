package com.artto.fastrepost.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.fastrepost.R
import com.artto.fastrepost.di.ApplicationLoader
import com.artto.fastrepost.di.module.ImageItemModule
import com.artto.fastrepost.presentation.presenter.ImageItemPresenter
import com.artto.fastrepost.presentation.view.ImageItemView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_content_image.*

class ImageItemFragment : BaseFragment(), ImageItemView {

    companion object {
        private const val KEY_DISPLAY_URL = "display_url"

        fun newInstance(displayUrl: String) = ImageItemFragment().apply {
            arguments = Bundle().apply { putString(KEY_DISPLAY_URL, displayUrl) }
        }
    }

    @InjectPresenter
    lateinit var presenter: ImageItemPresenter

    @ProvidePresenter
    fun providePresenter(): ImageItemPresenter =
            ApplicationLoader
                    .applicationComponent
                    .imageItemComponent(ImageItemModule())
                    .imageItemPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.item_content_image, container, false)

    override fun showImage() {
        Glide.with(image_view_repost_content)
                .load(arguments?.getString(KEY_DISPLAY_URL))
                .into(image_view_repost_content)
    }

}