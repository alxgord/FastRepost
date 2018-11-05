package com.artto.fastrepost.ui.history

class HistoryRecyclerAdapterContract {

    interface AdapterPresenter {
        fun getItemCount(): Int
        fun onBindViewHolder(viewHolder: ItemView, position: Int)
    }

    interface ItemPresenter {
        fun onRemoveClicked(position: Int)
    }

    interface ItemView {
        fun setImage(url: String)
        fun setOwner(owner: String)
        fun setCaption(caption: String)
    }
}