package br.com.felipepalm14.eventour.di.app.module


import br.com.felipepalm14.eventour.di.app.main.FragmentMainBindingModule
import br.com.felipepalm14.eventour.di.app.main.MainActivityModule
import br.com.felipepalm14.eventour.ui.MainActivity
import br.org.geledes.juntas.di.app.scope.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class, FragmentMainBindingModule::class])
    internal abstract fun bindMainActivity(): MainActivity


}