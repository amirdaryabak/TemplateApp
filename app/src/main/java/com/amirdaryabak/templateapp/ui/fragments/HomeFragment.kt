package com.amirdaryabak.templateapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.databinding.FragmentHomeBinding
import com.amirdaryabak.templateapp.eventbus.MyEvent
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by viewModels()
    @Inject
    lateinit var eventBus: EventBus

    val TAG = "HomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.button.setOnClickListener {
            eventBus.register(this)
            Toasty.info(requireContext(), "Registered Successfully").show()
        }

        binding.button2.setOnClickListener {
            eventBus.unregister(this)
            Toasty.error(requireContext(), "Unregistered Successfully").show()
        }


    }

    override fun onStart() {
        super.onStart()
        eventBus.register(this)
    }

    override fun onStop() {
        super.onStop()
        eventBus.unregister(this)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMyEvent(myEvent: MyEvent) {
        Toasty.success(requireContext(), myEvent.message).show()
    }

}
