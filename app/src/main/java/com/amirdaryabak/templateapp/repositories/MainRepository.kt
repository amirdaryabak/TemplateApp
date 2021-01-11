package com.amirdaryabak.templateapp.repositories

import com.amirdaryabak.templateapp.api.MyApi
import com.amirdaryabak.templateapp.api.ResourceTest
import com.amirdaryabak.templateapp.db.MyDao
import java.io.IOException
import javax.inject.Inject

class MainRepository @Inject constructor(
    val myDao: MyDao,
    val myApi: MyApi
) {

    /*suspend fun apiCall(p1: String, p2: Int, p3: Int): ResourceTest<> {
        return try {
            val response = myApi.search(p1, p2, p3)
            if (response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    return@let ResourceTest.success(resultResponse)
                } ?: ResourceTest.error("خطا نامعلوم", null)
            } else {
                ResourceTest.error("خطا در برقراری ارتباط با سرور", null)
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> ResourceTest.error("اتصال شما به اینترنت برقرار نمیباشد", null)
                else -> ResourceTest.error("Catch ERROR", null)
            }
        }
    }*/

}