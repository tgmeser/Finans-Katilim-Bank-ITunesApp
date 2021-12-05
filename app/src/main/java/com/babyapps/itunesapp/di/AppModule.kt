package com.babyapps.itunesapp.di

import com.babyapps.itunesapp.data.api.ITunesApi
import com.babyapps.itunesapp.data.repository.ITunesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ITunesApi.BASE_URL).build()

    @Provides
    @Singleton
    fun provideITunesApi(retrofit: Retrofit): ITunesApi = retrofit.create(ITunesApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: ITunesApi): ITunesRepository = ITunesRepository(api)

}