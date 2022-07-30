package com.juarez.marvelheroes.di

import com.juarez.marvelheroes.api.MarvelApi
import com.juarez.marvelheroes.characters.data.CharactersRepository
import com.juarez.marvelheroes.characters.data.CharactersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(marvelApi: MarvelApi): CharactersRepository {
        return CharactersRepositoryImpl(marvelApi)
    }
}