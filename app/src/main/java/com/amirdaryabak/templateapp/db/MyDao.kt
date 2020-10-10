package com.amirdaryabak.templateapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.amirdaryabak.templateapp.db.tables.MyDataModel

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myDataModel: MyDataModel)

    @Delete
    suspend fun delete(myDataModel: MyDataModel)

    @Query("SELECT * FROM my_table_name")
    fun observeAllItems(): LiveData<List<MyDataModel>>

}