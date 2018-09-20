package com.artto.fastrepost.presentation.presenter

import com.arellomobile.mvp.InjectViewState
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
                        { viewState.setImage(it.displayUrl) },
                        { viewState.showToast(it.localizedMessage) })
                .let { disposables.add(it) }
    }
}