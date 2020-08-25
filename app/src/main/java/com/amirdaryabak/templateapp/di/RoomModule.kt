package com.amirdaryabak.templateapp.di

import android.content.Context
import androidx.room.Room
import com.amirdaryabak.templateapp.db.MyDao
import com.amirdaryabak.templateapp.db.MyDataBase
import com.amirdaryabak.templateapp.other.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): MyDataBase {
        return Room
            .databaseBuilder(
                context,
                MyDataBase::class.java,
                DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(myDataBase: MyDataBase): MyDao {
        return myDataBase.getMyDao()
    }
}