package com.amirdaryabak.templateapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_first.*

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {

    private val viewModel: MainViewModel by viewModels()

    val TAG = "FirstFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtFirst.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

    }

}
