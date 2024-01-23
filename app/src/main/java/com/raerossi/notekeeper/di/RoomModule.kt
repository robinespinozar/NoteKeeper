package com.raerossi.notekeeper.di

import android.content.Context
import androidx.room.Room
import com.raerossi.notekeeper.data.local.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val TASK_DATABASE = "task_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(context, DataBase::class.java, TASK_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(database: DataBase) = database.getTaskDao()

    @Singleton
    @Provides
    fun provideCategoryDao(database: DataBase) = database.getCategoryDao()
}