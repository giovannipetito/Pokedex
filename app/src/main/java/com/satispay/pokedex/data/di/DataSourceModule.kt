package com.satispay.pokedex.data.di

import com.satispay.pokedex.data.datasource.remote.UsersDataSource
import com.satispay.pokedex.data.datasource.remote.impl.UsersDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideUsersDataSource(dataSource: UsersDataSourceImpl): UsersDataSource {
        return dataSource
    }
}