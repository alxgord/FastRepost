package com.artto.fastrepost.ui.history

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryItemViewHolder(itemView: View,
                            private val presenter: HistoryRecyclerAdapterContract.ItemPresenter) :
        RecyclerView.ViewHolder(itemView),
        HistoryRecyclerAdapterContract.ItemView {

    init {
        itemView.image_view_history_item_delete.setOnClickListener {
            presenter.onRemoveClicked(adapterPosition)
        }
    }

    override fun setImage(url: String) {
        Glide.with(itemView)
                .load(url)
                .into(itemView.image_view_history_item_content)
    }

    override fun setOwner(owner: String) {
        itemView.text_view_history_item_owner.text = owner
    }

    override fun setCaption(caption: String) {
        itemView.text_view_history_item_caption.text = caption
    }
}