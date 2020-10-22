package com.amirdaryabak.templateapp.ui.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.pressBack
import androidx.test.filters.MediumTest
import com.amirdaryabak.templateapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class FirstFragmentViewPager2Test {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun pressBackButton_popBackStack() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<FirstFragmentViewPager2>(
//            fragmentFactory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        pressBack()

        verify(navController).popBackStack()
    }
}