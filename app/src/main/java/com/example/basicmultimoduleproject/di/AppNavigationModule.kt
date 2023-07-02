package com.example.basicmultimoduleproject.di

import com.example.basicmultimoduleproject.impl.CharacterNavigationImpl
import com.example.character.ui.CharacterNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppNavigationModule : AppNavigationProvider{

    @get:Provides
    override val rickAndMortyListFragmentNavigation: CharacterNavigation
        get() = CharacterNavigationImpl()
}