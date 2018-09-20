package com.artto.fastrepost.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.fastrepost.R
import com.artto.fastrepost.di.ApplicationLoader
import com.artto.fastrepost.di.module.MainActivityModule
import com.artto.fastrepost.presentation.presenter.MainPresenter
import com.artto.fastrepost.presentation.view.MainView
import com.artto.fastrepost.ui.fragment.RepostFragment

class MainActivity : BaseActivity(), MainView {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter =
            ApplicationLoader
                    .applicationComponent
                    .mainActivityComponent(MainActivityModule())
                    .mainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToRepost() = navigateToFragment(RepostFragment(), false)

    private fun navigateToFragment(fragment: Fragment, addToBackStack: Boolean) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_main_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .apply { if (addToBackStack) addToBackStack(null) }
                .commit()
    }
}
