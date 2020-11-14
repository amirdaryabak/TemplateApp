package com.amirdaryabak.templateapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.databinding.ActivityMainBinding
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar(binding.toolbar)

        // radius for bottomNavigationView
        val radius = resources.getDimension(R.dimen.radius_small)
        val bottomNavigationViewBackground =
            binding.bottomNavigationView.background as MaterialShapeDrawable
        bottomNavigationViewBackground.shapeAppearanceModel =
            bottomNavigationViewBackground.shapeAppearanceModel.toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED, radius)
                .setTopLeftCorner(CornerFamily.ROUNDED, radius)
                .build()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(findNavController(navHostFragment))

        findNavController(navHostFragment)
            .addOnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                    R.id.homeFragment -> {
//                        binding.toolbar.visibility = View.VISIBLE
                        binding.bottomNavigationView.visibility = View.VISIBLE
                    }
                    R.id.firstFragmentViewPager2 -> {
//                        binding.toolbar.visibility = View.VISIBLE
                        binding.bottomNavigationView.visibility = View.GONE
                    }
                }
            }
    }
}