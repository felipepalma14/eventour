package br.com.felipepalm14.eventour.di.app.module

import android.app.Application
import android.content.Context
import br.com.felipepalm14.eventour.di.app.qualifiers.ApplicationContext
import br.com.felipepalm14.eventour.domain.database.AppDatabase
import br.com.felipepalm14.eventour.domain.database.EventDao
import br.com.felipepalm14.eventour.domain.repository.EventRemoteDataSource
import br.com.felipepalm14.eventour.domain.repository.EventRepository
import br.org.geledes.juntas.di.app.scope.AppScoped
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [( ViewModelFactoryModule::class)])
class AppModule {

    @AppScoped
    @Provides
    fun provideApplication(application: Application) : Context {
        return application.applicationContext
    }

    @AppScoped
    @Provides
    fun provideDatabase(application: Application) = AppDatabase.getDatabase(application)

    @AppScoped
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.eventDao()

    @AppScoped
    @Provides
    fun provideRepository(remoteDataSource: EventRemoteDataSource,
                          localDataSource: EventDao) =
        EventRepository(remoteDataSource, localDataSource)


    @AppScoped
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

}