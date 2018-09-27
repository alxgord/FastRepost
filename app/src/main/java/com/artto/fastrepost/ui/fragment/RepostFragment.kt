package com.artto.fastrepost.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.fastrepost.R
import com.artto.fastrepost.di.ApplicationLoader
import com.artto.fastrepost.di.module.RepostModule
import com.artto.fastrepost.presentation.presenter.RepostPresenter
import com.artto.fastrepost.presentation.view.RepostView
import com.artto.fastrepost.ui.adapter.ContentPagerAdapter
import kotlinx.android.synthetic.main.fragment_repost.*

class RepostFragment : BaseFragment(), RepostView {

    @InjectPresenter
    lateinit var presenter: RepostPresenter

    @ProvidePresenter
    fun providePresenter() =
            ApplicationLoader
                    .applicationComponent
                    .repostComponent(RepostModule())
                    .repostPresenter()

    private lateinit var contentPagerAdapter: ContentPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, p2: Bundle?): View? =
            inflater.inflate(R.layout.fragment_repost, container, false)

    override fun onViewCreated(p0: View, p1: Bundle?) {
        super.onViewCreated(p0, p1)
        button_repost_save.setOnClickListener { presenter.onSaveClicked() }
        button_repost_repost.setOnClickListener { presenter.onRepostClicked() }

        contentPagerAdapter = ContentPagerAdapter(childFragmentManager, presenter)
        view_pager_repost_content.adapter = contentPagerAdapter
        view_pager_repost_content.offscreenPageLimit = 10

        circle_indicator_repost_content.setViewPager(view_pager_repost_content)
        contentPagerAdapter.registerDataSetObserver(circle_indicator_repost_content.dataSetObserver)
    }

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_LONG).show()

    override fun showIndicator(show: Boolean) {
        circle_indicator_repost_content.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun notifyDataSetChanged() = contentPagerAdapter.notifyDataSetChanged()

    override fun setCaption(text: String) = edit_text_repost_caption.setText(text)

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }
}