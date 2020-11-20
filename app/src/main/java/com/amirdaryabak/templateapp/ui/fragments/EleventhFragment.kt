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
import com.amirdaryabak.templateapp.databinding.FragmentEleventhBinding
import com.amirdaryabak.templateapp.databinding.FragmentTestBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class EleventhFragment : Fragment(R.layout.fragment_eleventh) {

    private lateinit var binding: FragmentEleventhBinding
    private val viewModel: MainViewModel by viewModels()

    val TAG = "EleventhFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEleventhBinding.bind(view)

    }

}
