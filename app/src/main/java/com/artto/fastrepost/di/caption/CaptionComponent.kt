package com.artto.fastrepost.di.caption

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.caption.CaptionPresenter
import dagger.Subcomponent

@Subcomponent(modules = [CaptionModule::class])
@ViewScope
interface CaptionComponent {

    fun captionPresenter(): CaptionPresenter

}