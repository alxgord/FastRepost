package com.artto.fastrepost.di.dialog.rate

import com.artto.fastrepost.data.properties.PropertiesManager
import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.dialog.rate.RateInteract
import com.artto.fastrepost.ui.dialog.rate.RatePresenter
import dagger.Module
import dagger.Provides

@Module
class RateModule {

    @Provides
    @ViewScope
    fun provideInteract(propertiesManager: PropertiesManager) = RateInteract(propertiesManager)

    @Provides
    @ViewScope
    fun providePresenter(interact: RateInteract) = RatePresenter(interact)

}