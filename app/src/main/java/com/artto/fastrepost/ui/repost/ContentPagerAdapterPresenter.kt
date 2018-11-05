package com.artto.fastrepost.ui.repost

import com.artto.fastrepost.data.instagram.response.InstagramPostContentItem

interface ContentPagerAdapterPresenter {

    fun getContentCount(): Int

    fun getContentItem(position: Int): InstagramPostContentItem?
}