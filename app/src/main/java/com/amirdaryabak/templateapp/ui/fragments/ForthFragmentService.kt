package com.amirdaryabak.templateapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.databinding.FragmentForthBinding
import com.amirdaryabak.templateapp.services.MyIntentService
import com.amirdaryabak.templateapp.services.MyService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_forth.*
import kotlinx.android.synthetic.main.fragment_forth.buttonStart
import kotlinx.android.synthetic.main.fragment_forth.buttonStop
import kotlinx.android.synthetic.main.fragment_forth.txtService
import kotlinx.android.synthetic.main.fragment_third.*

@AndroidEntryPoint
class ForthFragmentService : Fragment(R.layout.fragment_forth) {

    private lateinit var binding: FragmentForthBinding
    private val viewModel: MainViewModel by viewModels()

    val TAG = "ThirdFragmentServices"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForthBinding.bind(view)

        binding.buttonStart.setOnClickListener {
            Intent(requireContext(), MyService::class.java).also {
                requireContext().startService(it)
                txtService.text = "Service running"
            }
        }
        binding.buttonStop.setOnClickListener {
            Intent(requireContext(), MyService::class.java).also {
                val data = "AmirService"
                it.putExtra("EXTRA_DATA", data)
                requireContext().stopService(it)
                txtService.text = "Service stopped"
            }
        }
        binding.buttonSendData.setOnClickListener {
            Intent(requireContext(), MyService::class.java).also {
                val data = if (etDataString.text.toString().isNotEmpty()) etDataString.text.toString() else ""
                it.putExtra("EXTRA_DATA", data)
                requireContext().startService(it)
            }
        }

    }

}
