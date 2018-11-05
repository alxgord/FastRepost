package com.artto.fastrepost.di.repost.image

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.repost.image.ImageItemPresenter
import dagger.Subcomponent

@Subcomponent(modules = [ImageItemModule::class])
@ViewScope
interface ImageItemComponent {

    fun imageItemPresenter(): ImageItemPresenter
}