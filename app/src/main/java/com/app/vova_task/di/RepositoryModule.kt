package com.app.vova_task.di


import com.app.vova_task.data.remote.repository.MillingsRepositoryImpl
import com.app.vova_task.domain.repository.MachinesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    abstract fun provideUnsplashImageRepository(impl: MillingsRepositoryImpl): MachinesRepository

}