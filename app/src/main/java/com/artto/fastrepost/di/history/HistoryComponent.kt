package com.artto.fastrepost.di.history

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.history.HistoryPresenter
import dagger.Subcomponent

@Subcomponent(modules = [HistoryModule::class])
@ViewScope
interface HistoryComponent {

    fun providePresenter(): HistoryPresenter
}