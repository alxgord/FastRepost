package com.artto.fastrepost.ui.repost

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.FileProvider
import android.text.method.ScrollingMovementMethod
import android.view.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.fastrepost.R
import com.artto.fastrepost.data.instagram.response.InstagramUserPost
import com.artto.fastrepost.ApplicationLoader
import com.artto.fastrepost.di.repost.RepostModule
import com.artto.fastrepost.ui.base.BaseFragment
import com.artto.fastrepost.ui.caption.CaptionFragment
import com.artto.fastrepost.ui.caption.CaptionRouter
import kotlinx.android.synthetic.main.fragment_repost.*
import java.io.File

class RepostFragment : BaseFragment(), RepostView, CaptionRouter {

    companion object {
        private const val TAG_FRAGMENT_CAPTION = "caption"

        fun newInstance() = RepostFragment()
    }

    @InjectPresenter
    lateinit var presenter: RepostPresenter

    @ProvidePresenter
    fun providePresenter() = ApplicationLoader
            .applicationComponent
            .repostComponent(RepostModule())
            .repostPresenter()

    private lateinit var contentPagerAdapter: ContentPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, p2: Bundle?): View? =
            inflater.inflate(R.layout.fragment_repost, container, false)

    override fun onViewCreated(p0: View, p1: Bundle?) {
        super.onViewCreated(p0, p1)

        text_view_repost_caption.movementMethod = ScrollingMovementMethod()
        text_view_repost_caption.setOnClickListener { presenter.onCaptionClicked(text_view_repost_caption.text.toString()) }

        button_repost_save.setOnClickListener { presenter.onSaveClicked(view_pager_repost_content.currentItem) }
        button_repost_repost.setOnClickListener {
            presenter.onRepostClicked(view_pager_repost_content.currentItem, text_view_repost_caption.text.toString())
        }

        contentPagerAdapter = ContentPagerAdapter(childFragmentManager, presenter)
        view_pager_repost_content.adapter = contentPagerAdapter
        view_pager_repost_content.offscreenPageLimit = 10

        circle_indicator_repost_content.setViewPager(view_pager_repost_content)
        contentPagerAdapter.registerDataSetObserver(circle_indicator_repost_content.dataSetObserver)
    }

    override fun showProgressBar(show: Boolean) {
        circular_progress_bar_repost_content.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showIndicator(show: Boolean) {
        circle_indicator_repost_content.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun notifyDataSetChanged() = contentPagerAdapter.notifyDataSetChanged()

    override fun setCaption(text: String) = text_view_repost_caption.setText(text)

    override fun sendIntentToInstagram(filePath: String, mediaType: String) {
        val intent = Intent()
        if (activity?.packageManager?.getLaunchIntentForPackage("com.instagram.android") != null)
            intent.apply {
                action = Intent.ACTION_SEND
                type = if (mediaType == InstagramUserPost.TYPE_VIDEO) "video/*" else "image/*"
                context?.let { putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(it, "com.artto.fastrepost.fileprovider", File(filePath))) }
                setPackage("com.instagram.android")
            } else {
            intent.apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                data = Uri.parse("market://details?id=com.instagram.android")
            }
        }
        startActivity(intent)
    }

    override fun showCaptionWindow(text: String) {
        childFragmentManager.beginTransaction()
                .add(R.id.frame_layout_repost_root_container, CaptionFragment.newInstance(text), TAG_FRAGMENT_CAPTION)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

    }

    override fun onWindowClosed(text: String) {
        text_view_repost_caption.text = text
        childFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun showHint(show: Boolean) {
        card_view_repost_hint.visibility = if (show) View.VISIBLE else View.GONE
        constraint_layout_repost_container.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

}