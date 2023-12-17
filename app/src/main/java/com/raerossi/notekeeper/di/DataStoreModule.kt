package com.raerossi.notekeeper.di

import android.content.Context
import com.raerossi.notekeeper.data.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

   // @Provides
   // @Singleton
   // fun provideDataStoreRepository(dataStore: DataStore): PreferencesRepository {
   //     return PreferencesRepository(dataStore)
   // }

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context): Preferences {
        return Preferences(context = context)
    }

   //@Provides
   //@Singleton
   //fun provideDataStore(@ApplicationContext context:Context): DataStore<Preferences> =  context.dataStore
}