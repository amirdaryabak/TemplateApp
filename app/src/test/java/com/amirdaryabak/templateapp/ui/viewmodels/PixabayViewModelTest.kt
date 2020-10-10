package com.amirdaryabak.templateapp.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amirdaryabak.templateapp.MainCoroutineRule
import com.amirdaryabak.templateapp.api.Status
import com.amirdaryabak.templateapp.getOrAwaitValueTest
import com.amirdaryabak.templateapp.other.Constants
import com.amirdaryabak.templateapp.repositories.FakePixabayRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PixabayViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: PixabayViewModel

    @Before
    fun setup() {
        viewModel = PixabayViewModel(FakePixabayRepository())
    }

    @Test
    fun `insert myDataModel item with empty field, returns error`() {
        viewModel.insertItem("")

        val value = viewModel.insertItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert myDataModel item with too long name, returns error`() {
        val string = buildString {
            for(i in 1..Constants.MAX_NAME_LENGTH + 1) {
                append(1)
            }
        }
        viewModel.insertItem(string)

        val value = viewModel.insertItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert myDataModel item with valid input, returns success`() {
        viewModel.insertItem("name")

        val value = viewModel.insertItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

}