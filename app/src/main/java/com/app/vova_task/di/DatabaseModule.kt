package com.app.vova_task.di

import android.content.Context
import androidx.room.Room
import com.app.vova_task.data.local.AptDatabase
import com.app.vova_task.data.local.dao.MillingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideMachineDao(appDatabase: AptDatabase): MillingDao = appDatabase.millingDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AptDatabase =
        Room.databaseBuilder(appContext, AptDatabase::class.java, "A_database").build()

}

