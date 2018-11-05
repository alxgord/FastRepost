package com.artto.fastrepost.ui.history

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.fastrepost.R
import com.artto.fastrepost.ApplicationLoader
import com.artto.fastrepost.di.history.HistoryModule
import com.artto.fastrepost.ui.base.BaseFragment
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : BaseFragment(), HistoryView {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    @InjectPresenter
    lateinit var presenter: HistoryPresenter

    @ProvidePresenter
    fun providePresenter() = ApplicationLoader
            .applicationComponent
            .historyComponent(HistoryModule())
            .providePresenter()

    private lateinit var recyclerLayoutManager: LinearLayoutManager
    private lateinit var recyclerAdapter: HistoryRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerLayoutManager = LinearLayoutManager(context)
        recyclerAdapter = HistoryRecyclerAdapter(presenter, presenter)
        recycler_view_history.layoutManager = recyclerLayoutManager
        recycler_view_history.adapter = recyclerAdapter

        ad_view.loadAd(AdRequest.Builder().build())
    }

    override fun notifyDataSetChanged() = recyclerAdapter.notifyDataSetChanged()

    override fun notifyItemRemoved(position: Int) = recyclerAdapter.notifyItemRemoved(position)
}