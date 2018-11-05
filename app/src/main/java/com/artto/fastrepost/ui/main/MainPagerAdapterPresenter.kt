package com.artto.fastrepost.ui.main

import android.support.v4.app.Fragment

interface MainPagerAdapterPresenter {

    fun getCount(): Int

    fun getItem(position: Int): Fragment

}