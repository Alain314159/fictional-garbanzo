package com.cerdita.app.di

import com.cerdita.app.data.remote.NostrRepositoryImpl
import com.cerdita.app.data.repository.NostrRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 🐷 Módulo Hilt para Nostr
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class NostrModule {
    
    @Binds
    @Singleton
    abstract fun bindNostrRepository(
        nostrRepositoryImpl: NostrRepositoryImpl
    ): NostrRepository
}
