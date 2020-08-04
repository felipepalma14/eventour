package br.com.felipepalm14.eventour.di.app.component

import android.app.Application
import br.com.felipepalm14.eventour.Eventour
import br.com.felipepalm14.eventour.di.app.module.APIModule
import br.com.felipepalm14.eventour.di.app.module.AppModule
import br.com.felipepalm14.eventour.domain.repository.EventRepository
import br.com.felipepalm14.eventour.di.app.module.ActivityBindingModule
import br.org.geledes.juntas.di.app.scope.AppScoped
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@AppScoped
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    AppModule::class,
    APIModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: Eventour)

    fun inject(eventRespository: EventRepository)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}