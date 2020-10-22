package com.amirdaryabak.templateapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.amirdaryabak.templateapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_view_pager2_content.*

@AndroidEntryPoint
class ViewPager2ContentFragment(private val pid: String) : Fragment(R.layout.fragment_view_pager2_content) {

    private val viewModel: MainViewModel by viewModels()

    val TAG = "SecondFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView.text = pid

    }

}
