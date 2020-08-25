package com.amirdaryabak.templateapp.repository

import com.amirdaryabak.templateapp.api.MyApi
import com.amirdaryabak.templateapp.db.MyDao
import com.amirdaryabak.templateapp.db.MyDataBase
import javax.inject.Inject

class MainRepository @Inject constructor(
    val myDao: MyDao,
    val myApi: MyApi
) {

}