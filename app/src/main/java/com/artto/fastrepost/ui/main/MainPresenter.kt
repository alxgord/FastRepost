package com.artto.fastrepost.ui.main

import android.support.v4.app.Fragment
import android.view.MenuItem
import com.arellomobile.mvp.InjectViewState
import com.artto.fastrepost.R
import com.artto.fastrepost.ui.base.BaseMvpPresenter
import com.artto.fastrepost.ui.history.HistoryFragment
import com.artto.fastrepost.ui.repost.RepostFragment
import java.lang.RuntimeException

@InjectViewState
class MainPresenter : BaseMvpPresenter<MainView>(), MainPagerAdapterPresenter {

    private val items = listOf(RepostFragment.newInstance(), HistoryFragment.newInstance())

    override fun onFirstViewAttach() {
        viewState.requestPermission()
    }

    fun onBottomNavigationItemSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.item_navigation_repost -> viewState.setViewPagerPosition(MainActivity.FRAGMENT_REPOST)
            R.id.item_navigation_history -> viewState.setViewPagerPosition(MainActivity.FRAGMENT_HISTORY)
            else -> RuntimeException("Invalid argument")
        }
    }

    fun onViewPagerItemChanged(position: Int) {
        when (position) {
            MainActivity.FRAGMENT_REPOST -> viewState.setBottomNavigationItem(R.id.item_navigation_repost)
            MainActivity.FRAGMENT_HISTORY -> viewState.setBottomNavigationItem(R.id.item_navigation_history)
            else -> RuntimeException("Invalid argument")
        }
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Fragment = items[position]
}