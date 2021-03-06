package com.amirdaryabak.templateapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.databinding.FragmentSeventhBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class SeventhFloatingActionButtonToBottomNavigationFragment : Fragment(R.layout.fragment_seventh) {

    private var _binding: FragmentSeventhBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    val TAG = "FloatingActionButtonToBottomNavigationFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSeventhBinding.bind(view)

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

    }

}
