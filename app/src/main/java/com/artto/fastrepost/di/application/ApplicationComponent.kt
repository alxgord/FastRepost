package com.artto.fastrepost.di.application

import com.artto.fastrepost.di.caption.CaptionComponent
import com.artto.fastrepost.di.caption.CaptionModule
import com.artto.fastrepost.di.data.ClipboardModule
import com.artto.fastrepost.di.data.DatabaseModule
import com.artto.fastrepost.di.data.InstagramApiModule
import com.artto.fastrepost.di.data.StorageModule
import com.artto.fastrepost.di.main.MainActivityComponent
import com.artto.fastrepost.di.history.HistoryComponent
import com.artto.fastrepost.di.history.HistoryModule
import com.artto.fastrepost.di.main.MainActivityModule
import com.artto.fastrepost.di.repost.RepostComponent
import com.artto.fastrepost.di.repost.RepostModule
import com.artto.fastrepost.di.repost.image.ImageItemComponent
import com.artto.fastrepost.di.repost.image.ImageItemModule
import com.artto.fastrepost.di.repost.video.VideoItemComponent
import com.artto.fastrepost.di.repost.video.VideoItemModule
import com.artto.fastrepost.di.scope.ApplicationScope
import dagger.Component

@Component(
        modules = [
            ApplicationModule::class,
            InstagramApiModule::class,
            ClipboardModule::class,
            StorageModule::class,
            DatabaseModule::class
        ]
)
@ApplicationScope
interface ApplicationComponent {

    fun mainActivityComponent(module: MainActivityModule): MainActivityComponent

    fun repostComponent(module: RepostModule): RepostComponent

    fun historyComponent(module: HistoryModule): HistoryComponent

    fun imageItemComponent(module: ImageItemModule): ImageItemComponent

    fun videoItemComponent(module: VideoItemModule): VideoItemComponent

    fun captptionComponent(module: CaptionModule): CaptionComponent

}