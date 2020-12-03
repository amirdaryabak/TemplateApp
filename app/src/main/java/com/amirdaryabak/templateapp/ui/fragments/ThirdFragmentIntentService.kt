package com.amirdaryabak.templateapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.databinding.FragmentThirdBinding
import com.amirdaryabak.templateapp.services.MyIntentService
import com.amirdaryabak.templateapp.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_third.*

@AndroidEntryPoint
class ThirdFragmentIntentService : Fragment(R.layout.fragment_third) {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    val TAG = "ThirdFragmentServices"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentThirdBinding.bind(view)

        binding.buttonStart.setOnClickListener {
            Intent(requireContext(), MyIntentService::class.java).also {
                requireActivity().startService(it)
                txtService.text = "IntentService running"
            }
        }
        binding.buttonStop.setOnClickListener {
            MyIntentService.stopService()
            txtService.text = "IntentService stopped"
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
