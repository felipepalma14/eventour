package br.com.felipepalm14.eventour

import br.com.felipepalm14.eventour.di.app.component.DaggerAppComponent
import br.com.felipepalm14.eventour.di.common.Provider
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class Eventour:  DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        appComponent.inject(this)
        Provider.appComponent = appComponent
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

    }

}