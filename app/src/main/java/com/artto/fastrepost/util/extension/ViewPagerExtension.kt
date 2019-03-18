package com.artto.fastrepost.util.extension

import android.support.v4.view.ViewPager

fun ViewPager.onPageSelectedListener(onPageSelected: (position: Int) -> Unit = {}) = addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) = onPageSelected.invoke(position)

})