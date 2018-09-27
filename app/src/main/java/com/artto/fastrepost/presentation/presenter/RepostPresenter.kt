package com.artto.fastrepost.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.artto.fastrepost.data.instagram.response.post.InstagramPostContentItem
import com.artto.fastrepost.data.instagram.response.post.InstagramUserPost
import com.artto.fastrepost.interact.RepostInteract
import com.artto.fastrepost.presentation.adapter.ContentPagerAdapterPresenter
import com.artto.fastrepost.presentation.view.RepostView

@InjectViewState
class RepostPresenter(private val interact: RepostInteract) : BaseMvpPresenter<RepostView>(), ContentPagerAdapterPresenter {

    private var currentPost: InstagramUserPost? = null

    override fun onFirstViewAttach() {
        interact
                .newPostUrl()
                .subscribe(
                        { loadPost(it) },
                        { viewState.showToast(it.localizedMessage) })
                .let { disposables.add(it) }
    }

    fun onResume() {
        interact
                .getPostUrl()
                .subscribe(
                        { loadPost(it) },
                        { viewState.showToast(it.localizedMessage) })
                .let { disposables.add(it) }
    }

    fun onSaveClicked() {

    }

    fun onRepostClicked() {

    }

    private fun loadPost(shortCode: String) {
        if (!currentPost?.shortCode.equals(shortCode))
            interact
                    .getPost(shortCode)
                    .subscribe(
                            { checkContentType(it) },
                            { it.printStackTrace() })
                    .let { disposables.add(it) }
    }

    private fun checkContentType(post: InstagramUserPost) {
        currentPost = post
        viewState.setCaption(post.caption)
        viewState.showIndicator(post.content.size > 1)
        viewState.notifyDataSetChanged()
    }

    override fun getContentCount(): Int = currentPost?.content?.size ?: 0

    override fun getContentItem(position: Int): InstagramPostContentItem? = currentPost?.content?.get(position)


}