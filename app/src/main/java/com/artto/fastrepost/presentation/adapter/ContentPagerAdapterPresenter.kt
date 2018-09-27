package com.artto.fastrepost.presentation.adapter

import com.artto.fastrepost.data.instagram.response.post.InstagramPostContentItem

interface ContentPagerAdapterPresenter {

    fun getContentCount(): Int

    fun getContentItem(position: Int): InstagramPostContentItem?
}