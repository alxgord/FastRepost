package com.artto.fastrepost.ui.history

import com.arellomobile.mvp.InjectViewState
import com.artto.fastrepost.data.database.history.HistoryEntity
import com.artto.fastrepost.ui.base.BaseMvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class HistoryPresenter(private val interact: HistoryInteract) :
        BaseMvpPresenter<HistoryView>(),
        HistoryRecyclerAdapterContract.AdapterPresenter,
        HistoryRecyclerAdapterContract.ItemPresenter {

    private val historyList = ArrayList<HistoryEntity>()

    override fun onFirstViewAttach() {
        loadHistory()
    }

    private fun loadHistory() =
            interact.getAllHistory()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .distinctUntilChanged { _, new -> new == historyList }
                    .subscribeBy(
                            onNext = {
                                historyList.clear()
                                historyList.addAll(it)
                                viewState.notifyDataSetChanged()
                            },
                            onError = {})
                    .addTo(disposables)

    override fun getItemCount(): Int = historyList.count()

    override fun onBindViewHolder(viewHolder: HistoryRecyclerAdapterContract.ItemView, position: Int) {
        interact.getPost(historyList[position].shortCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            try {
                                viewHolder.setCaption(it.caption)
                                viewHolder.setImage(it.content[historyList[position].mediaPosition].displayUrl)
                                viewHolder.setOwner(it.ownerUserName)
                            } catch (e: Exception) {
                            }
                        },
                        onError = { loadHistory() })
                .addTo(disposables)
    }

    override fun onRemoveClicked(position: Int) {
        interact.delete(historyList[position])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onSuccess = {
                    historyList.removeAt(position)
                    viewState.notifyItemRemoved(position)
                }, onError = {})
                .addTo(disposables)
    }
}