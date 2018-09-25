package com.artto.fastrepost.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.artto.fastrepost.data.instagram.response.post.InstagramUserPost
import com.artto.fastrepost.interact.RepostInteract
import com.artto.fastrepost.presentation.view.RepostView

@InjectViewState
class RepostPresenter(private val interact: RepostInteract) : BaseMvpPresenter<RepostView>() {

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
        interact
                .getPost(shortCode)
                .subscribe(
                        { checkContentType(it) },
                        { it.printStackTrace() })
                .let { disposables.add(it) }
    }

    private fun checkContentType(post: InstagramUserPost) {
        viewState.setCaption (post.caption)
        when (post.type) {
            InstagramUserPost.TYPE_VIDEO -> viewState.setVideo(post.content[0].videoUrl)
            InstagramUserPost.TYPE_IMAGE, InstagramUserPost.TYPE_SIDECAR -> viewState.setImage(post.content[0].displayUrl)
        }
    }
}