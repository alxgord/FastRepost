package com.artto.fastrepost.di.component

import com.artto.fastrepost.di.module.*
import com.artto.fastrepost.di.scope.ApplicationScope
import dagger.Component

@Component(
        modules = [
            ApplicationModule::class,
            InstagramApiModule::class,
            ClipboardModule::class
        ]
)
@ApplicationScope
interface ApplicationComponent {

    fun mainActivityComponent(mainActivityModule: MainActivityModule): MainActivityComponent

    fun repostComponent(repostModule: RepostModule): RepostComponent

    fun imageItemComponent(imageItemModule: ImageItemModule): ImageItemComponent

    fun videoItemComponent(videoItemModule: VideoItemModule): VideoItemComponent
}