package com.artto.fastrepost.ui.activity

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.fastrepost.R
import com.artto.fastrepost.di.ApplicationLoader
import com.artto.fastrepost.di.module.MainActivityModule
import com.artto.fastrepost.presentation.presenter.MainPresenter
import com.artto.fastrepost.presentation.view.MainView

class MainActivity : BaseActivity(), MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter =
            ApplicationLoader.applicationComponent
                    .mainActivityComponent(MainActivityModule())
                    .mainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
