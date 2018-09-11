package com.artto.fastrepost.di

import android.app.Application
import com.artto.fastrepost.di.component.ApplicationComponent
import com.artto.fastrepost.di.component.DaggerApplicationComponent
import com.artto.fastrepost.di.module.ApplicationModule

class ApplicationLoader : Application() {

    companion object {

        private lateinit var application: ApplicationLoader

        val applicationComponent
            get() = application.applicationComponent

    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        application = this
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

    }


}