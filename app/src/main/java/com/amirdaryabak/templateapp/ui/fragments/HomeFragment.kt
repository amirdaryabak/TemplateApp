package com.amirdaryabak.templateapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.databinding.FragmentHomeBinding
import com.amirdaryabak.templateapp.eventbus.MyEvent
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.amirdaryabak.templateapp.utils.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    @Inject
    lateinit var eventBus: EventBus
    @Inject
    lateinit var dataStore: DataStore<Preferences>

    val TAG = "HomeFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.imageView.setOnClickListener {
            val extras = FragmentNavigatorExtras(binding.imageView to "image_big")
            findNavController().navigate(
                R.id.action_homeFragment_to_imageFragment,
                null,
                null,
                extras
            )
        }

        binding.button.setOnClickListener {
            if (!eventBus.isRegistered(this)) {
                eventBus.register(this)
                Toasty.info(requireContext(), "Registered Successfully").show()
            } else {
                Toasty.info(requireContext(), "Already Registered!3").show()
            }
        }

        binding.button2.setOnClickListener {
            eventBus.unregister(this)
            Toasty.error(requireContext(), "Unregistered Successfully").show()
        }

        lifecycleScope.launch {
            saveToDataStore("key", "DataStoreValue")
            Toasty.success(requireContext(), readFromDataStore("key").toString()).show()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.tasksEvent.collect { event ->
                when (event) {
                    is MainViewModel.HandleEvent.Obj -> TODO()
                    is MainViewModel.HandleEvent.DT -> TODO()
                }.exhaustive
            }
        }

    }

    private suspend fun saveToDataStore(key: String, value: String) {
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    private suspend fun readFromDataStore(key: String): String? {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMyEvent(myEvent: MyEvent) {
        Toasty.success(requireContext(), myEvent.message).show()
    }

    override fun onStart() {
        super.onStart()
        eventBus.register(this)
    }

    override fun onStop() {
        super.onStop()
        eventBus.unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
