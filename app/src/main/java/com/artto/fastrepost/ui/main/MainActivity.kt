package com.artto.fastrepost.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.fastrepost.R
import com.artto.fastrepost.ApplicationLoader
import com.artto.fastrepost.di.main.MainActivityModule
import com.artto.fastrepost.ui.base.BaseActivity
import com.artto.fastrepost.util.extension.onPageSelectedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {

    companion object {
        const val FRAGMENT_REPOST = 0
        const val FRAGMENT_HISTORY = 1
    }

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter =
            ApplicationLoader
                    .applicationComponent
                    .mainActivityComponent(MainActivityModule())
                    .mainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(view_pager_main_container) {
            adapter = MainPagerAdapter(supportFragmentManager, presenter)
            offscreenPageLimit = 2
            onPageSelectedListener { presenter.onViewPagerItemChanged(it) }
        }

        bottom_navigation_view_main.setOnNavigationItemSelectedListener {
            presenter.onBottomNavigationItemSelected(it)
            true
        }
    }

    override fun setViewPagerPosition(position: Int) {
        view_pager_main_container.currentItem = position
    }

    override fun setBottomNavigationItem(itemId: Int) {
        bottom_navigation_view_main.selectedItemId = itemId
    }

    override fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        requestPermission()
    }
}
