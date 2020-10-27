package com.amirdaryabak.templateapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.adapters.ViewPagerAdapter
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*

@AndroidEntryPoint
class FirstFragmentViewPager2 : Fragment(R.layout.fragment_first) {

    private val viewModel: MainViewModel by viewModels()

    val TAG = "FirstFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = arrayListOf(
            SecondFragmentNotifications(),
            ThirdFragmentIntentService(),
            ForthFragmentService(),
            FifthFragmentDragAndDrop(),
            SixthFragmentBroadcastReceiver(),
            SeventhFloatingActionButtonToBottomNavigationFragment(),
            EighthFragmentOpenContextMenuToAView(),
        )
        val fragmentsNameList = arrayListOf(
            "SecondFragmentNotifications",
            "ThirdFragmentIntentService",
            "ForthFragmentService",
            "FifthFragmentDragAndDrop",
            "SixthFragmentBroadcastReceiver",
            "SeventhFloatingActionButtonToBottomNavigationFragment",
            "EighthFragmentOpenContextMenuToAView",
        )
        setUpViewPagerAdapter(fragments)
        setUpTabLayoutMediator(fragmentsNameList, tabLayout, viewPager)

    }

    private fun setUpTabLayoutMediator(
        fragmentNameList: java.util.ArrayList<String>,
        tabLayout: TabLayout,
        viewPager: ViewPager2
    ) {
//        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = fragmentNameList[position]
        }.attach()
    }

    private fun setUpViewPagerAdapter(list: ArrayList<Fragment>) {
        viewPager.adapter = ViewPagerAdapter(
            list,
            childFragmentManager,
            lifecycle
        )
//        viewPager.isUserInputEnabled = false
    }

}
