package com.satispay.pokedex.data.di

import com.satispay.pokedex.data.datasource.remote.PokemonDataSource
import com.satispay.pokedex.data.datasource.remote.impl.PokemonDataSourceImpl
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
    fun providePokemonDataSource(dataSource: PokemonDataSourceImpl): PokemonDataSource {
        return dataSource
    }
}