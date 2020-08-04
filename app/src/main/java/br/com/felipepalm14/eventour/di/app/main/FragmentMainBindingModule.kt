package br.com.felipepalm14.eventour.di.app.main

import br.com.felipepalm14.eventour.ui.event.EventListFragment
import br.org.geledes.juntas.di.app.scope.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentMainBindingModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun bindEventListFragment(): EventListFragment

}