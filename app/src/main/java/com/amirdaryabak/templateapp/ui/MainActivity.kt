package com.amirdaryabak.templateapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.amirdaryabak.templateapp.R
import com.amirdaryabak.templateapp.ui.viewmodels.MainViewModel
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // radius for bottomNavigationView
        val radius = resources.getDimension(R.dimen.radius_small)
        val bottomNavigationViewBackground =
            bottomNavigationView.background as MaterialShapeDrawable
        bottomNavigationViewBackground.shapeAppearanceModel =
            bottomNavigationViewBackground.shapeAppearanceModel.toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED, radius)
                .setTopLeftCorner(CornerFamily.ROUNDED, radius)
                .build()

        bottomNavigationView.setupWithNavController(nav_host_fragment.findNavController())

        nav_host_fragment.findNavController()
            .addOnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                    R.id.homeFragment -> {
                        toolbar.visibility = View.GONE
                        bottomNavigationView.visibility = View.VISIBLE
                    }
                    R.id.firstFragmentViewPager2 -> {
                        toolbar.visibility = View.GONE
                        bottomNavigationView.visibility = View.GONE
                    }
                }
            }
    }
}