package com.artto.fastrepost.ui.repost

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.PagerAdapter
import com.artto.fastrepost.data.instagram.response.InstagramUserPost
import com.artto.fastrepost.ui.repost.image.ImageItemFragment
import com.artto.fastrepost.ui.repost.video.VideoItemFragment
import eu.inloop.pager.UpdatableFragmentPagerAdapter
import java.lang.RuntimeException

class ContentPagerAdapter(manager: FragmentManager,
                          private val adapterPresenter: ContentPagerAdapterPresenter) : UpdatableFragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment = adapterPresenter.getContentItem(position).let {
        when (it?.type) {
            InstagramUserPost.TYPE_IMAGE -> ImageItemFragment.newInstance(it.cacheUrl)
            InstagramUserPost.TYPE_VIDEO -> VideoItemFragment.newInstance(it.cacheUrl)
            else -> throw RuntimeException("Invalid argument")
        }
    }

    override fun getCount(): Int = adapterPresenter.getContentCount()

    override fun getItemPosition(item: Any): Int = PagerAdapter.POSITION_NONE

    override fun getItemId(position: Int): Long = adapterPresenter.getContentItem(position)?.id?.toLong() ?: 0
}