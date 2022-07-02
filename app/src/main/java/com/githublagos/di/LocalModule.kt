package com.githublagos.di

import android.content.Context
import androidx.room.Room
import com.githublagos.data.local.room.FavouriteDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideFavouriteDb(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FavouriteDb::class.java,
        "Favourite"
    ).build()

    @Singleton
    @Provides
    fun provideFavouriteDao(db: FavouriteDb) = db.getDao()
}