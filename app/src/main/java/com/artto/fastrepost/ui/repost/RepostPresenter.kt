package com.artto.fastrepost.ui.repost

import com.arellomobile.mvp.InjectViewState
import com.artto.fastrepost.R
import com.artto.fastrepost.data.instagram.response.InstagramPostContentItem
import com.artto.fastrepost.data.instagram.response.InstagramUserPost
import com.artto.fastrepost.ui.base.BaseMvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

@InjectViewState
class RepostPresenter(private val interact: RepostInteract) : BaseMvpPresenter<RepostView>(), ContentPagerAdapterPresenter {

    private var currentPost: InstagramUserPost? = null

    override fun onFirstViewAttach() {
        viewState.showHint(true)
        interact.newPostUrl()
                .subscribe(
                        { loadPost(it) },
                        { viewState.showToast(it.localizedMessage) })
                .addTo(disposables)
    }

    fun onResume() {
        interact.getPostUrl()
                .subscribe(
                        { loadPost(it) },
                        { viewState.showToast(it.localizedMessage) })
                .addTo(disposables)
    }

    fun onCaptionClicked(text: String) = viewState.showCaptionWindow(text)

    fun onSaveClicked(position: Int) = currentPost?.let { post ->
        interact.savePostContentItem(post.content[position])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { viewState.showToast("Saved to $it") },
                        onError = { viewState.showToast(it.localizedMessage) })
    }

    fun onRepostClicked(position: Int, caption: String) = currentPost?.let { post ->
        viewState.sendIntentToInstagram(post.content[position].cacheUrl, post.content[position].type)
        interact.setClipText(caption)
                .andThen(interact.addToHistory(post.shortCode, position))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onComplete = { viewState.showToast(R.string.repost_caption_hint) },
                        onError = {})
    }

    private fun loadPost(shortCode: String) {
        if (currentPost?.shortCode?.compareTo(shortCode) != 0)
            interact.getPost(shortCode)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        viewState.showHint(false)
                        viewState.showProgressBar(true)
                    }
                    .doOnEvent { _, _ -> viewState.showProgressBar(false) }
                    .subscribeBy(
                            onSuccess = { checkContentType(it) },
                            onError = {
                                viewState.showHint(true)
                                if (it is UnknownHostException)
                                    viewState.showToast(R.string.repost_toast_offline)
                            })
                    .addTo(disposables)
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