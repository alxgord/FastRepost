package com.artto.fastrepost.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import com.artto.fastrepost.data.instagram.response.post.InstagramUserPost
import com.artto.fastrepost.presentation.adapter.ContentPagerAdapterPresenter
import com.artto.fastrepost.ui.fragment.ImageItemFragment
import com.artto.fastrepost.ui.fragment.VideoItemFragment
import eu.inloop.pager.UpdatableFragmentPagerAdapter

class ContentPagerAdapter(manager: FragmentManager,
                          private val adapterPresenter: ContentPagerAdapterPresenter) : UpdatableFragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment = adapterPresenter.getContentItem(position).let {
        when (it?.type) {
            InstagramUserPost.TYPE_IMAGE -> ImageItemFragment.newInstance(it.displayUrl)
            InstagramUserPost.TYPE_VIDEO -> VideoItemFragment.newInstance(it.videoUrl)
            else -> ImageItemFragment.newInstance("")
        }
    }

    override fun getCount(): Int = adapterPresenter.getContentCount()

    override fun getItemPosition(item: Any): Int = PagerAdapter.POSITION_NONE

    override fun getItemId(position: Int): Long = adapterPresenter.getContentItem(position)?.id?.toLong() ?: 0
}