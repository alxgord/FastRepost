package com.artto.fastrepost

import android.app.Application
import com.artto.fastrepost.di.application.ApplicationComponent
import com.artto.fastrepost.di.application.ApplicationModule
import com.artto.fastrepost.di.application.DaggerApplicationComponent
import com.google.android.gms.ads.MobileAds

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

        MobileAds.initialize(this, "ca-app-pub-6538899012774414~8318148652")
    }

}