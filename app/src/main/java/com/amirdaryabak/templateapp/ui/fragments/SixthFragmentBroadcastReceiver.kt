package com.amirdaryabak.templateapp.ui.fragments

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.broadcastreceiver.MyBroadcastReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SixthFragmentBroadcastReceiver : Fragment(R.layout.fragment_sixth) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var receiver: MyBroadcastReceiver

    val TAG = "SixthFragmentBroadcastReceiver"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receiver = MyBroadcastReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            requireContext().registerReceiver(receiver, it)
        }

    }

    override fun onStop() {
        super.onStop()
        requireContext().unregisterReceiver(receiver)
    }
}
