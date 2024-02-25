package com.example.travelappinterview.di

import com.example.travelappinterview.common.Constants
import com.example.travelappinterview.data.remote.TravelApi
import com.example.travelappinterview.data.repository.TravelRepositoryImpl
import com.example.travelappinterview.domain.repository.TravelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by AidenChang 2024/02/22
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTravelApi(): TravelApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TravelApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTravelRepository(api: TravelApi): TravelRepository {
        return TravelRepositoryImpl(api = api)
    }
}