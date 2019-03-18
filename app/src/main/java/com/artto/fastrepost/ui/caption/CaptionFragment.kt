package com.artto.fastrepost.ui.caption

import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.artto.fastrepost.ApplicationLoader
import com.artto.fastrepost.R
import com.artto.fastrepost.di.caption.CaptionModule
import com.artto.fastrepost.ui.base.BaseFragment
import com.artto.fastrepost.util.extension.hideKeyboard
import kotlinx.android.synthetic.main.fragment_caption.*
import kotlinx.android.synthetic.main.fragment_caption.view.*

class CaptionFragment : BaseFragment(), CaptionView {

    companion object {
        private const val KEY_CAPTION_TEXT = "caption_text"

        fun newInstance(text: String) = CaptionFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_CAPTION_TEXT, text)
            }
        }
    }

    @InjectPresenter
    lateinit var presenter: CaptionPresenter

    @ProvidePresenter
    fun providePresenter() = ApplicationLoader
            .applicationComponent
            .captionComponent(CaptionModule())
            .captionPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_caption, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edit_text_caption.setText(arguments?.getString(KEY_CAPTION_TEXT))
        button_caption_ok.setOnClickListener { presenter.onOkClicked() }
    }

    override fun dismissCaptionWindow() {
        edit_text_caption.hideKeyboard()
        (router as CaptionRouter).onWindowClosed(edit_text_caption.text.toString())
    }

    override fun onResume() {
        super.onResume()
        view?.apply {
            edit_text_caption.setOnKeyListener { _, i, _ ->
                if (i == KeyEvent.KEYCODE_BACK) {
                    edit_text_caption.clearFocus()
                    requestFocus()
                    true
                } else false
            }

            isFocusableInTouchMode = true
            requestFocus()
            setOnKeyListener { _, code, event ->
                if (event.action == MotionEvent.ACTION_UP && code == KeyEvent.KEYCODE_BACK) {
                    presenter.onBackPressed()
                    true
                } else false
            }
        }
    }
}