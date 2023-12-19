package com.raerossi.notekeeper.di

import android.content.Context
import com.raerossi.notekeeper.data.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context): Preferences = Preferences(context = context)
}