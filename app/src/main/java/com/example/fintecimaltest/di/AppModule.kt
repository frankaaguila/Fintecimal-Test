package com.example.fintecimaltest.di

import android.content.Context
import com.example.fintecimaltest.data.local.AppDatabase
import com.example.fintecimaltest.data.local.TripDao
import com.example.fintecimaltest.data.remote.TripRemoteDataSource
import com.example.fintecimaltest.data.remote.TripService
import com.example.fintecimaltest.data.repository.TripRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://fintecimal-test.herokuapp.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideTripService(retrofit: Retrofit): TripService = retrofit.create(TripService::class.java)

    @Singleton
    @Provides
    fun provideTripRemoteDataSource(TripService: TripService) = TripRemoteDataSource(TripService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideTripDao(db: AppDatabase) = db.tripDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: TripRemoteDataSource,
                          localDataSource: TripDao
    ) = TripRepository(remoteDataSource, localDataSource)
}