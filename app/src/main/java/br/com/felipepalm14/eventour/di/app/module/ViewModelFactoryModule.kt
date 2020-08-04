package br.com.felipepalm14.eventour.di.app.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.felipepalm14.eventour.di.common.ViewModelFactory
import br.com.felipepalm14.eventour.di.common.ViewModelKey
import br.com.felipepalm14.eventour.ui.viewmodel.MainViewModel
import br.org.geledes.juntas.di.app.scope.AppScoped
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @AppScoped
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainActivityViewModel(mainViewModel: MainViewModel): ViewModel


}