package com.artto.fastrepost.ui.base

import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.artto.fastrepost.util.CustomToast

abstract class BaseFragment : MvpAppCompatFragment(), BaseMvpView {

    protected val router: BaseRouter
        get() = (parentFragment ?: activity) as BaseRouter

    fun showToast(text: String) {
        context?.let { CustomToast.makeText(it, text, Toast.LENGTH_LONG).show() }
    }

    fun showToast(textResId: Int) {
        context?.let { CustomToast.makeText(it, getString(textResId), Toast.LENGTH_LONG).show() }
    }

}