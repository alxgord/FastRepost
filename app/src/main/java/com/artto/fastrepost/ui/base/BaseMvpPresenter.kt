package com.artto.fastrepost.ui.base

import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable

abstract class BaseMvpPresenter<T : BaseMvpView> : MvpPresenter<T>() {

    protected val disposables = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}