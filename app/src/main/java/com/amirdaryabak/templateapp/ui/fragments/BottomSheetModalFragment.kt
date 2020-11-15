package com.amirdaryabak.templateapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.api.Resource
import com.amirdaryabak.templateapp.databinding.ActivityMainBinding
import com.amirdaryabak.templateapp.databinding.FragmentTestBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class BottomSheetModalFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentTestBinding
    private val viewModel: MainViewModel by viewModels()

    val TAG = "TestFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTestBinding.bind(view)

    }

}
