package com.satispay.pokedex.data.di

import android.content.Context
import androidx.room.Room
import com.satispay.pokedex.data.dao.PokemonDao
import com.satispay.pokedex.data.database.PokedexRoomDatabase
import com.satispay.pokedex.data.datasource.local.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDatabaseModule {

    @Provides
    @Singleton
    fun providePokedexRoomDatabase(@ApplicationContext context: Context): PokedexRoomDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = PokedexRoomDatabase::class.java,
            name = "pokedex_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(database: PokedexRoomDatabase): PokemonDao = database.pokemonDao()

    @Provides
    @Singleton
    fun provideRoomRepository(pokemonDao: PokemonDao): RoomRepository = RoomRepository(pokemonDao = pokemonDao)
}