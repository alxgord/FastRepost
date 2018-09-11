package com.artto.fastrepost.di.component

import com.artto.fastrepost.di.module.*
import com.artto.fastrepost.di.scope.ApplicationScope
import dagger.Component

@Component( modules = [ApplicationModule::class] )
@ApplicationScope
interface ApplicationComponent {

    fun mainActivityComponent(mainActivityModule: MainActivityModule): MainActivityComponent
}