package com.amirdaryabak.templateapp.repositories

import com.amirdaryabak.templateapp.api.MyApi
import com.amirdaryabak.templateapp.db.MyDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    val myDao: MyDao,
    val myApi: MyApi
) {

}