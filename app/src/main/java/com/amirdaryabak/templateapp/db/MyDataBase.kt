package com.amirdaryabak.templateapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amirdaryabak.templateapp.models.MyDataModel

@Database(
    entities = [MyDataModel::class],
    version = 1
)
abstract class MyDataBase : RoomDatabase(){

    abstract fun getMyDao(): MyDao
}