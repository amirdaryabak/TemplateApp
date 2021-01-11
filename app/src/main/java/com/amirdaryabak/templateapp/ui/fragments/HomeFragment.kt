package com.amirdaryabak.templateapp.ui.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
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
import com.amirdaryabak.templateapp.api.Status
import com.amirdaryabak.templateapp.databinding.FragmentHomeBinding
import com.amirdaryabak.templateapp.eventbus.MyEvent
import com.amirdaryabak.templateapp.other.Constants
import com.amirdaryabak.templateapp.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.amirdaryabak.templateapp.other.Constants.REQUEST_CODE_LOCATION_PERMISSION
import com.amirdaryabak.templateapp.other.Constants.REQUEST_CODE_LOCATION_PERMISSION_ON_START_SERVICE
import com.amirdaryabak.templateapp.services.TrackingService
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.amirdaryabak.templateapp.utils.TrackingUtility
import com.amirdaryabak.templateapp.utils.exhaustive
import com.amirdaryabak.templateapp.utils.toasty
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), EasyPermissions.PermissionCallbacks {

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

        requestPermissions(REQUEST_CODE_LOCATION_PERMISSION)

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
                Toasty.info(requireContext(), "Already Registered!").show()
            }
        }

        binding.button2.setOnClickListener {
            if (eventBus.isRegistered(this)) {
                eventBus.unregister(this)
                Toasty.error(requireContext(), "Unregistered Successfully").show()
            } else {
                Toasty.info(requireContext(), "Already Unregistered!").show()
            }
        }

        binding.button3.setOnClickListener {
            if (TrackingUtility.hasLocationPermissions(requireContext())) {
                Intent(requireContext(), TrackingService::class.java).also {
                    it.action = ACTION_START_OR_RESUME_SERVICE
                    requireContext().startService(it)
                }
                toasty(requireContext(), "Service Started, Checkout Notification", 2)
            } else {
                requestPermissions(REQUEST_CODE_LOCATION_PERMISSION_ON_START_SERVICE)
            }
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

    private fun requestPermissions(requestCode: Int) {
        if (TrackingUtility.hasLocationPermissions(requireContext())) {
            return
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                requestCode,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions(REQUEST_CODE_LOCATION_PERMISSION)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION) {
            toasty(requireContext(), "PermissionsGranted", 2)
        } else if (requestCode == REQUEST_CODE_LOCATION_PERMISSION_ON_START_SERVICE) {
            binding.button3.performClick()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        eventBus.unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /*private fun getUserType() {
        val fragments = arrayListOf<Fragment>()
        val fragmentsNameList = arrayListOf<String>()
        viewModel.apiCall(p1, p2, p3).observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { response ->
                when (response.status) {
                    Status.SUCCESS -> {
                        loading.visibility = View.GONE
                        response.data?.let {

                        }
                    }
                    Status.ERROR -> {
                        loading.visibility = View.GONE

                    }
                    Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                    }
                }
            }
        }
    }*/

}
