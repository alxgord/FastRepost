package com.artto.fastrepost.ui.dialog.rate

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.fastrepost.ApplicationLoader
import com.artto.fastrepost.R
import com.artto.fastrepost.di.dialog.rate.RateModule
import com.artto.fastrepost.ui.base.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_rate.view.*

class RateDialogFragment : BaseDialogFragment(), RateView {

    @InjectPresenter
    lateinit var presenter: RatePresenter

    @ProvidePresenter
    fun providePresenter() = ApplicationLoader
            .applicationComponent
            .rateComponent(RateModule())
            .ratePresenter()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity?.layoutInflater?.inflate(R.layout.dialog_rate, null, false)?.apply {
            button_remind_later.setOnClickListener { presenter.onRemindClicked() }
            button_rate.setOnClickListener { presenter.onRateClicked() }
        }

        return AlertDialog.Builder(activity)
                .setView(view)
                .setCancelable(true)
                .create().apply { window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) }
    }

    override fun navigateToPlayMarket() {
        val appPackageName = context?.packageName

        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
        } catch (e: android.content.ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
        }
    }

    override fun dismissDialog() = dismiss()

}