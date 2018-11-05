package com.artto.fastrepost.ui.history

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.artto.fastrepost.R

class HistoryRecyclerAdapter(private val adapterPresenter: HistoryRecyclerAdapterContract.AdapterPresenter,
                             private val itemPresenter: HistoryRecyclerAdapterContract.ItemPresenter) : RecyclerView.Adapter<HistoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): HistoryItemViewHolder =
            HistoryItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false), itemPresenter)

    override fun getItemCount(): Int = adapterPresenter.getItemCount()

    override fun onBindViewHolder(viewHolder: HistoryItemViewHolder, position: Int) =
            adapterPresenter.onBindViewHolder(viewHolder, position)

}