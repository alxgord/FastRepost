package com.artto.fastrepost.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainPagerAdapter(manager: FragmentManager,
                       private val adapterPresenter: MainPagerAdapterPresenter) : FragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment = adapterPresenter.getItem(position)

    override fun getCount(): Int = adapterPresenter.getCount()
}