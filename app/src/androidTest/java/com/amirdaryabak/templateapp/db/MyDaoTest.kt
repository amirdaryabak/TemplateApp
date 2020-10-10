package com.amirdaryabak.templateapp.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.amirdaryabak.templateapp.db.tables.MyDataModel
import com.amirdaryabak.templateapp.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class MyDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: MyDataBase
    private lateinit var dao: MyDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.getMyDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insert() = runBlockingTest {
        val myDataModel = MyDataModel("name")
        dao.insert(myDataModel)

        val allShoppingItems = dao.observeAllItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(myDataModel)
    }

    @Test
    fun delete() = runBlockingTest {
        val myDataModel = MyDataModel("name")
        dao.insert(myDataModel)
        dao.delete(myDataModel)

        val allShoppingItems = dao.observeAllItems().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(myDataModel)
    }

}