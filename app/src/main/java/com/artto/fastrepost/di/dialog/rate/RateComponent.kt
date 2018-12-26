package com.artto.fastrepost.di.dialog.rate

import com.artto.fastrepost.di.scope.ViewScope
import com.artto.fastrepost.ui.dialog.rate.RatePresenter
import dagger.Subcomponent

@Subcomponent(modules = [RateModule::class])
@ViewScope
interface RateComponent {

    fun ratePresenter(): RatePresenter

}