package com.artto.fastrepost.di.repost.video

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.repost.video.VideoItemPresenter
import dagger.Subcomponent

@Subcomponent(modules = [VideoItemModule::class])
@ViewScope
interface VideoItemComponent {

    fun videoItemPresenter(): VideoItemPresenter
}