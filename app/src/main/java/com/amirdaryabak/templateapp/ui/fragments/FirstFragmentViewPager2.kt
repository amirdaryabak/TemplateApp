package com.amirdaryabak.templateapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.adapters.ViewPagerAdapter
import com.amirdaryabak.templateapp.databinding.FragmentFirstBinding
import com.amirdaryabak.templateapp.exoplayer.ui.FacebookScreenFragment
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_first.*

@AndroidEntryPoint
class FirstFragmentViewPager2 : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private val args: FirstFragmentViewPager2Args by navArgs()

    val TAG = "FirstFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)

        Toasty.success(requireContext(), args.name.toString()).show()

        val fragments = arrayListOf(
            SecondFragmentNotifications(),
            ThirdFragmentIntentService(),
            ForthFragmentService(),
            FifthFragmentDragAndDrop(),
            SixthFragmentBroadcastReceiver(),
            SeventhFloatingActionButtonToBottomNavigationFragment(),
            EighthFragmentOpenContextMenuToAView(),
            NinthFragment(),
            TenthFragment(),
            FacebookScreenFragment(),
        )
        val fragmentsNameList = arrayListOf(
            "SecondFragmentNotifications",
            "ThirdFragmentIntentService",
            "ForthFragmentService",
            "FifthFragmentDragAndDrop",
            "SixthFragmentBroadcastReceiver",
            "SeventhFloatingActionButtonToBottomNavigationFragment",
            "EighthFragmentOpenContextMenuToAView",
            "NinthFragment",
            "TenthFragment",
            "FacebookScreenFragment",
        )
        setUpViewPagerAdapter(fragments)
        setUpTabLayoutMediator(fragmentsNameList, binding.tabLayout, binding.viewPager)

    }

    private fun setUpTabLayoutMediator(
        fragmentNameList: java.util.ArrayList<String>,
        tabLayout: TabLayout,
        viewPager: ViewPager2
    ) {
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
