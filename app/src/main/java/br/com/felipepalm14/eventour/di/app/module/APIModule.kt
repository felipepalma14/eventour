package br.com.felipepalm14.eventour.di.app.module

import br.com.felipepalm14.eventour.BASE_URL
import br.com.felipepalm14.eventour.domain.network.EventsAPI
import br.org.geledes.juntas.di.app.scope.AppScoped
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class APIModule {
    @AppScoped
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        return  OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200,TimeUnit.SECONDS)
            .build()

    }
    @AppScoped
    @Provides
    fun provideGson(): GsonConverterFactory {
        return  GsonConverterFactory.create()

    }
    @AppScoped
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }
    @AppScoped
    @Provides
    fun provideEventsService(retrofit: Retrofit): EventsAPI = retrofit.create(EventsAPI::class.java)

}