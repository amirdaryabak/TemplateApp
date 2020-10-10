package com.amirdaryabak.templateapp.di

import com.amirdaryabak.templateapp.api.MyApi
import com.amirdaryabak.templateapp.db.MyDao
import com.amirdaryabak.templateapp.repositories.DefaultPixabayRepository
import com.amirdaryabak.templateapp.repositories.PixabayRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoriesModule {

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: MyDao,
        api: MyApi
    ) = DefaultPixabayRepository(dao, api) as PixabayRepository

}