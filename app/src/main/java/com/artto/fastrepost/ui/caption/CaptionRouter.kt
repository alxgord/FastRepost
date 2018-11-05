package com.artto.fastrepost.ui.caption

import com.artto.fastrepost.ui.base.BaseRouter

interface CaptionRouter : BaseRouter {

    fun onWindowClosed(text: String)

}